<%@ page import="checkpoint.grails.ReconController" %>
<!DOCTYPE html>
<head>
  <title>Recon</title>

  <meta name="layout" content="main"/>
  <r:require modules="bootstrap"/>

</head>
<body>
  <div id="index">

    <div class="container">
      <br />
      <br />

      <div class="row">
        <h4>Properties</h4>
        <div class="col-md-6">
          <p>
            Action: ${soapAction}<br />
            Uri: ${uri}
          </p>
        </div>

        <div class="col-md-6">
          <p>
            Hotel Code: ${hotelCode}<br />
            Username: ${username}<br />
            Password: ${password}
          </p>
        </div>
      </div>

      <g:if test=${xmlRequest}>
        <div class="row">
          <h4>Request</h4>
          <div class="col-md-12">
            <div class="well well-sm">
              <p>
                ${xmlRequest}
              </p>
            </div>
          </div>
        </div>
      </g:if>  

      <g:if test=${xmlResponse}>
        <div class="row">
          <h4>Response</h4>
          <div class="col-md-12">
            <div class="well well-sm">
              <p>
                ${xmlResponse}
              </p>
            </div>
          </div>
        </div>
     </g:if>

      <g:if test="${xmlError}">
        <div class="row">
          <h4>Error</h4>
          <div class="col-md-12">
            <p class="alert alert-danger" role="alert">${xmlError}</p>
          </div>
        </div>
      </g:if>
    </div>
  </div>
</body>
</html>
