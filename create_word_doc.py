import zipfile
from pathlib import Path

output = Path('LibraryFiles.docx')
if output.exists():
    output.unlink()

content_paragraphs = [
    ('Library Management System File Summary', 'Heading1'),
    ('This file lists the main project files and package groups in the Java library system.', None),
    ('', None),
    ('Main files', 'Heading1'),
    ('src/main/java/library/LibraryApp.java - main console application and menu.', None),
    ('src/main/java/library/Library.java - central library manager.', None),
    ('', None),
    ('library.items package', 'Heading1'),
    ('src/main/java/library/items/LibraryItem.java - base abstract class for books, magazines, and theses.', None),
    ('src/main/java/library/items/Borrowable.java - interface for borrowable behavior.', None),
    ('src/main/java/library/items/Book.java - concrete Book item type.', None),
    ('src/main/java/library/items/Magazine.java - concrete Magazine item type.', None),
    ('src/main/java/library/items/Thesis.java - concrete Thesis item type.', None),
    ('', None),
    ('library.members package', 'Heading1'),
    ('src/main/java/library/members/Member.java - abstract member base class.', None),
    ('src/main/java/library/members/StandardMember.java - variant 0 member tier.', None),
    ('src/main/java/library/members/PremiumMember.java - variant 0 member tier.', None),
    ('src/main/java/library/members/VIPMember.java - variant 0 member tier.', None),
    ('src/main/java/library/members/BasicMember.java - variant 1 member tier.', None),
    ('src/main/java/library/members/SilverMember.java - variant 1 member tier.', None),
    ('src/main/java/library/members/GoldMember.java - variant 1 member tier.', None),
    ('src/main/java/library/members/RegularMember.java - variant 2 member tier.', None),
    ('src/main/java/library/members/PlusMember.java - variant 2 member tier.', None),
    ('src/main/java/library/members/EliteMember.java - variant 2 member tier.', None),
    ('', None),
    ('library.exceptions package', 'Heading1'),
    ('src/main/java/library/exceptions/ItemNotAvailableException.java - item availability exception.', None),
    ('src/main/java/library/exceptions/BorrowLimitExceededException.java - borrow limit exception.', None),
    ('', None),
    ('library.utils package', 'Heading1'),
    ('src/main/java/library/utils/SearchResult.java - generic search helper class.', None),
    ('src/main/java/library/utils/MemberFactory.java - creates member objects by variant.', None),
    ('src/main/java/library/utils/StudentConfig.java - stores student variant configuration.', None),
    ('', None),
    ('Note', 'Heading1'),
    ('The Markdown summary file FILES_OVERVIEW.md is also included in the project.', None),
]

def make_paragraph(text, style=None):
    style_attr = f' w:val="{style}"' if style else ''
    return f'<w:p><w:pPr>{f"<w:pStyle{style_attr}/>": if style else ""}</w:pPr><w:r><w:t>{text}</w:t></w:r></w:p>'

# Create valid XML content
body_content = ''.join(make_paragraph(text, style) for text, style in content_paragraphs)

# Minimal styles XML
styles_xml = '''<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:styles xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
  <w:style w:type="paragraph" w:styleId="Normal">
    <w:name w:val="Normal"/>
    <w:qFormat/>
  </w:style>
  <w:style w:type="paragraph" w:styleId="Heading1">
    <w:name w:val="heading 1"/>
    <w:basedOn w:val="Normal"/>
    <w:next w:val="Normal"/>
    <w:rPr><w:b/></w:rPr>
  </w:style>
</w:styles>'''

document_xml = f'''<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<w:document xmlns:w="http://schemas.openxmlformats.org/wordprocessingml/2006/main">
  <w:body>
    {body_content}
    <w:sectPr>
      <w:pgSz w:w="11906" w:h="16838"/>
      <w:pgMar w:top="1440" w:right="1440" w:bottom="1440" w:left="1440" w:header="720" w:footer="720" w:gutter="0"/>
    </w:sectPr>
  </w:body>
</w:document>'''

content_types = '''<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Types xmlns="http://schemas.openxmlformats.org/package/2006/content-types">
  <Default Extension="rels" ContentType="application/vnd.openxmlformats-package.relationships+xml"/>
  <Default Extension="xml" ContentType="application/xml"/>
  <Override PartName="/word/document.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.document.main+xml"/>
  <Override PartName="/word/styles.xml" ContentType="application/vnd.openxmlformats-officedocument.wordprocessingml.styles+xml"/>
</Types>'''

rels_rels = '''<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument" Target="word/document.xml"/>
</Relationships>'''

word_rels = '''<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<Relationships xmlns="http://schemas.openxmlformats.org/package/2006/relationships">
  <Relationship Id="rId1" Type="http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles" Target="styles.xml"/>
</Relationships>'''

with zipfile.ZipFile(output, 'w', zipfile.ZIP_DEFLATED) as docx:
    docx.writestr('[Content_Types].xml', content_types)
    docx.writestr('_rels/.rels', rels_rels)
    docx.writestr('word/document.xml', document_xml)
    docx.writestr('word/_rels/document.xml.rels', word_rels)
    docx.writestr('word/styles.xml', styles_xml)

print(f'Created {output}')
