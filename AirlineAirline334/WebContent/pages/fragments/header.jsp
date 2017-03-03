<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<header>
	 	<div id="header" class="container-fluid">
		    <div class="navbar-header col-md-8">
		    	<h1>Airline Logo</h1>
		    </div>
	    	<div class="col-md-4 dropdown">
	    		<s:if test="%{#session.userInfo}">
					<button id="loginButton" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				     	 <h5><strong>${userInfo.firstName} ${userInfo.lastName}</strong></h5>
				    </button>
				    <div id="userWrapper" class="dropdown-menu dropdown-menu-right" role="menu">
				    	<div id="lineColor">
				    		<s:text var="userRole" name="%{#session.userInfo.type}"/>
				    		<strong><s:property value="#userRole.toUpperCase()"/></strong>
				    	</div>
				    	<div class="center-myAccount">
							<div id="userImage">
								<img src="./images/userIcon.png" class="img-circle img-thumbnail" alt="User Photo">
							</div>
							<div id="userInfo">
								<a type="button" class="btn btn-success btn-block" href="my-flights">My Flights</a>
								<a type="button" class="btn btn-success btn-block" href="logout">Log out</a>
								<!-- Sample only -->
								<a type="button" class="btn btn-success btn-block" href="add-ons">Addons</a>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<button id="loginButton" type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
				      <h5>Log In</h5>
				    </button>
				    <div id="loginWrapper" class="dropdown-menu dropdown-menu-right" role="menu">
				    	<div id="lineColorLogin">
				    		<h4>
				    			<span class="glyphicon glyphicon-log-in"></span> Log In
				    			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				    		</h4>
				    	</div>
						<form id="loginForm" action="LoginAction" method="post">
							<div class="form-group">
						    	<label for="username">Username</label>
						    	<input type="text" class="form-control" id="username" name="username">
						  	</div>
						  	<div class="form-group">
						    	<label for="pwd">Password</label>
						    	<input type="password" class="form-control" id="password" name="password">
						  	</div>
						  	<p class="errorMessage"><i>${errorMessage}</i></p>
						  	<button type="submit" class="btn btn-success btn-block">Submit</button>
						</form>
					</div>
				</s:else>
			</div>
		</div>
</header>