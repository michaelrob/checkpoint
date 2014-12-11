<!DOCTYPE html>
<head>
    <title>Recon</title>

    <meta name="layout" content="main"/>
    <r:require modules="bootstrap"/>

</head>
<body>
<div id="index">

    <div class="container">

        <form class="index" id="validateXml" method="post">
            <br />
            <br />

            <div class="row">
                <div class="col-md-6">

                    <div class="form-group">
                        <label for="endpoint">Endpoint</label>
                        <input type="text" class="form-control" id="endpoint" placeholder="Endpoint">
                    </div>
               </div>

                <div class="col-md-6">

                    <div class="form-group">
                        <label for="username">Username</label>
                        <input type="text" class="form-control" id="username" placeholder="Username">
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="text" class="form-control" id="password" placeholder="Password">
                    </div>
                </div>
            </div>

        <g:actionSubmit value="submit" action="show" type="button" class="btn btn-primary"/>
        </form>


    </div>
</div>
</body>
</html>
