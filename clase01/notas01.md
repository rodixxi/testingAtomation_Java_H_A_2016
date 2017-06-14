*[..Indice](https://github.com/rodixxi/testingAtomation_Java_H_A_2016)*

# Introducccion a automatizacion y Xpath
## Clase 01
### -- Xpath --

**Ejemplos de Xpath y xml**

**Ejemplo en clase**

```xml
<p></p>

<xml>
	<property>
		<name type="String">Juan</name>
		<lastName type="String">Cho</lastName>
		<age type="int">32</age>
	</property>
</xml>
```
```xpath
//*[text()='32'] trae el nodo  age
//*[@type="String"] trae el nodo name y lastname
```

**Ejemplo de [W3School](https://www.w3schools.com/xml/xml_xpath.asp)**

```xml
<?xml version="1.0" encoding="UTF-8"?>

<bookstore>

<book category="cooking">
  <title lang="en">Everyday Italian</title>
  <author>Giada De Laurentiis</author>
  <year>2005</year>
  <price>30.00</price>
</book>

<book category="children">
  <title lang="en">Harry Potter</title>
  <author>J K. Rowling</author>
  <year>2005</year>
  <price>29.99</price>
</book>

<book category="web">
  <title lang="en">XQuery Kick Start</title>
  <author>James McGovern</author>
  <author>Per Bothner</author>
  <author>Kurt Cagle</author>
  <author>James Linn</author>
  <author>Vaidyanathan Nagarajan</author>
  <year>2003</year>
  <price>49.99</price>
</book>

<book category="web">
  <title lang="en">Learning XML</title>
  <author>Erik T. Ray</author>
  <year>2003</year>
  <price>39.95</price>
</book>

</bookstore>
```

**Como llamar con Xpath**

```xpath
/bookstore/book[1]	Selects the first book element that is the child of the bookstore element
/bookstore/book[last()]	Selects the last book element that is the child of the bookstore element
/bookstore/book[last()-1]	Selects the last but one book element that is the child of the bookstore element
/bookstore/book[position()<3]	Selects the first two book elements that are children of the bookstore element
//title[@lang]	Selects all the title elements that have an attribute named lang
//title[@lang='en']	Selects all the title elements that have a "lang" attribute with a value of "en"
/bookstore/book[price>35.00]	Selects all the book elements of the bookstore element that have a price element with a value greater than 35.00
/bookstore/book[price>35.00]/title	Selects all the title elements of the book elements of the bookstore element that have a price element with a value greater than 35.00
```

##### -- Para jugar en chrome dev tool --

```jQuery
$x("//*") 
```
*Es una funcion incluida en chrome*

## Ejercicios para practicar

- [Ejercicio1](https://rodixxi.github.io/testingAtomation_Java_H_A_2016/clase01/xpath%20ex/1.html)
- [Ejercicio2](https://rodixxi.github.io/testingAtomation_Java_H_A_2016/clase01/xpath%20ex/2.html)
- [Ejercicio3](https://rodixxi.github.io/testingAtomation_Java_H_A_2016/clase01/xpath%20ex/3.html)

*[.Inicio](#)*
