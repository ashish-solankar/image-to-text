# image-to-text
java project to extract text from given image

It requires tesseract to be installed on machine, to install please visit https://tesseract-ocr.github.io/tessdoc/Installation.html

## How to Use
```
    public static void main(String[] args) {
        String text =  TextFinder.findText("/workspaces/image-to-text/finder/src/main/resources/test.png");
        System.out.println("Text found: " + text);
    }
```