<!DOCTYPE html>
<head>
    <title>Recon</title>

    <meta name="layout" content="main"/>
    <r:require modules="bootstrap"/>

</head>
<body>
<div id="index">

    <div class="container">

        <g:form name="reconForm" controller="recon" action="show">
            <br />
            <br />

            <div class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="endpoint">Endpoint</label>
                        <input type="text" class="form-control" name="uri" placeholder="Endpoint">
                    </div>
               </div>

                <div class="col-md-6">

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" name="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text" class="form-control" name="password" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <label for="hotelCode">Hotel Code</label>
                        <input type="text" class="form-control" name="hotelCode" placeholder="Hotel Code">
                    </div>
                </div>
            </div>

            <g:actionSubmit value="submit" action="show" type="button" class="btn btn-primary"/>
        </g:form>


    </div>
</div>
</body>
</html>
