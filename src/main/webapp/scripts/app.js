﻿/*
POPUP call function:
popup({popup: '#tablePopup',show: true,centralize: true,overlay: true,overlayID: '#overlay'});
*/

$(document).ready(function() {	
	
	tabs(); // payment system tabs switcher

    $('#slider').twirlie({
        transtime: 300
    });

    $('.floorNumbers a').live( 'click', function(){
        // attr  floornumber is added for defining floor
        var floor = $(this).attr('floornumber');
        $('#slider').gotoSlide(floor);
    });

    $('#slider').changeRoom({
        number : 'room'
    })

    $(".room").live("mouseover mouseout", function(event) {
        var roomTextEl =  $('.roomNumber');
        var floorNumberText = $(this).attr('floor');
        var roomNumberText = $(this).attr('room');
        var calculatedRoomNumber = parseInt(floorNumberText)*100 + parseInt(roomNumberText);

        if ( event.type == "mouseover" ) {
               $('.roomNumber span').text(calculatedRoomNumber);
               roomTextEl.positionOn($(this), 'center');
               roomTextEl.show();
        } else {
            roomTextEl.hide();
        }
        return false;
    });
    $('.goodMining').gradienter({ color_start: '56cb56', color_end: '165c16' });
    $('.badMining').gradienter({ color_start: 'cb5656', color_end: '5c1616' });

    $('.goodNetwork').gradienter({ color_start: '56cb56', color_end: '165c16' });
    $('.badNetwork').gradienter({ color_start: 'cb5656', color_end: '5c1616' });

    $('.goodDuty').gradienter({ color_start: '56cb56', color_end: '165c16' });
    $('.badDuty').gradienter({ color_start: 'cb5656', color_end: '5c1616' });

});

function tabs() {
	$('.tabContent li').click(function(e) {
        console.log('ok');
		e.preventDefault();
		$(this).siblings().removeClass('current');
		$(this).addClass('current');
		var tab_name = $('.tabContent li.current a').attr('href');
		$('.tabText').children("'"+tab_name+"'").show().siblings().hide();
	});
}