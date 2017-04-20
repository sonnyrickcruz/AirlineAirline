<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<div id="myCarousel" class="carousel slide" data-ride="carousel">
	<!-- Indicators -->
	<ol class="carousel-indicators">
		<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
		<li data-target="#myCarousel" data-slide-to="1"></li>
		<li data-target="#myCarousel" data-slide-to="2"></li>
		<li data-target="#myCarousel" data-slide-to="3"></li>
	</ol>

	<!-- Wrapper for slides -->
	<div class="carousel-inner" role="listbox">
		<div class="item active">
			<img src="./images/airplane1.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Quote of the day!</h3>
				<p>When everything seems to be going against you, remember that
					the airplane takes off against the wind, not with it.</p>
				<p class="quoteAuthor">- Henry Ford</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane3.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Discount!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane2.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Promo!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>
		<div class="item">
			<img src="./images/airplane4.jpg" alt="Airplane">
			<div class="carousel-caption">
				<h3>Advertisement!</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
					Quisque eu semper est. Phasellus vehicula pretium commodo. Duis in
					ullamcorper enim. Mauris venenatis porta elit id malesuada.
					Pellentesque eu venenatis sem. Aliquam erat volutpat. Pellentesque
					hendrerit turpis nec quam venenatis, et luctus leo sodales. Cras id
					orci efficitur, blandit nibh a, lacinia purus.</p>
			</div>
		</div>

		<div class="right carousel-control">
			<div id="bookingWrapper">This is the field where the user can
				search for available flights.</div>
		</div>
	</div>

</div>
<form id="airplaneForm" action="aircraftAction" method="POST">
	<button type="submit">AIRPLANE</button>
</form>
<form id="airportForm" action="airportAction" method="POST">
	<button type="submit">AIRPORT</button>
</form>
<a type="button" class="btn btn-success btn-block" href="add-ons">Addons</a>