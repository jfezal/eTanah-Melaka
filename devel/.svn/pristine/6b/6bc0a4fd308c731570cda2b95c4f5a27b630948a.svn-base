/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This javascript file checks for the brower/browser tab action.
 * It is based on the file menstioned by Daniel Melo.
 * Reference: http://stackoverflow.com/questions/1921941/close-kill-the-session-when-the-browser-or-tab-is-closed
 */
var validNavigation = false;

function endSession() {
  // Browser or broswer tab is closed
  // Do sth here ...    
  $.post(url + 'logout');  
}

function wireUpEvents() {
  /*
  * For a list of events that triggers onbeforeunload on IE
  * check http://msdn.microsoft.com/en-us/library/ms536907(VS.85).aspx
  * fixme : chrome does not valid for this function
  */ 
 
 if (navigator.userAgent.indexOf('AppletWebKit') > -1) window.onbeforeunload = endSession;
 else  {  
      window.onbeforeunload = function() {          
          if (!validNavigation) {
             endSession();
          }
      } 
 }
   

  // Attach the event keypress to exclude the F5 refresh
  $('html').bind('keypress', function(e) {
    if (e.keyCode == 116){
      validNavigation = true;
    }
  });  

  // Attach the event click for all links in the page
  $("a").bind("click", function() {
    validNavigation = true;
  });

  // Attach the event submit for all forms in the page
  $("form").bind("submit", function() {
    validNavigation = true;
  });

  // Attach the event click for all inputs in the page
  $("input[type=submit]").bind("click", function() {
    validNavigation = true;
  });
  
}

// Wire up the events as soon as the DOM tree is ready
$(document).ready(function() {  
  wireUpEvents();  
});