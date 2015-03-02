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
            <b>Action:</b> ${soapAction}<br />
            <b>Uri:</b> ${uri}
          </p>
        </div>

        <div class="col-md-6">
          <p>
            <b>Hotel Code:</b> ${hotelCode}<br />
            <b>Username:</b> ${username}<br />
            <b>Password:</b> ${password}
          </p>
        </div>
      </div>

      <div class="row">
        <h4>Room Types</h4>
      </div>

      <div class="dropdown">
        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-expanded="true">
          Available Room Types
        </button>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
          <g:each var="rooms" in="${roomTypes}">
            <li role="presentation"><a role="menuitem" tabindex="-1" href="#">${room.name}</a></li>
          </g:each>
        </ul>
      </div>

      <g:each var="rooms" in="${roomTypes}">
        <div class="row">
          <div class="col-md-12">
            <p>
              <b>Room Name:</b> ${rooms.name}<br />
              <b>Room Code:</b> ${rooms."room-type-code"}<br />
              <b>Room Description:</b> ${rooms."room-type-name"}<br />
              <b>Rate Code:</b> ${rooms."rate-plan-code"}<br />
              <b>Rate Description:</b> ${rooms."rate-plan-name"}
            </p>
          </div>
        </div>
      </g:each>


      <g:if test="${xmlRequest}">
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

      <g:if test="${xmlResponse}">
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
