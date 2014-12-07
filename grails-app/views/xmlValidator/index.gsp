<!DOCTYPE html>
  <head>
    <title>XML Validator</title>

    <meta name="layout" content="main"/>
    <r:require modules="bootstrap"/>

  </head>
  <body>
    <div id="index">

      <div class="container">

        <form class="index" id="validateXml" method="post">
          <br />
          <br />

          <h4>XML</h4>
          <g:textArea name="xml" rows="5" cols="40"/><br />
          <h4>XSD</h4>
          <g:textArea name="xsd" rows="5" cols="40"/>

          <br />
          <g:actionSubmit value="submit" action="show" type="button" class="btn btn-primary"/>
        </form>
      </div>
    </div>
  </body>
</html>
