<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="dashboard">
	<form>
	  	<div class="form-group">
		    <label for="origin">Origin:</label>
		    <input type="text" class="form-control" id="selectOrigin">
	  	</div>
	  	<div class="form-group">
		    <label for="destination">Destination:</label>
		    <input type="text" class="form-control" id="selectDestination">
	  	</div>
	 	<button type="submit" class="btn btn-default">View Route</button>
	</form>
</div>