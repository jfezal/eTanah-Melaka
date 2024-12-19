<%--
    Document   : calender_enkuiri
    Created on : Jul 21, 2010, 2:04:00 PM
    Author     : ctzainal
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>


<link rel='stylesheet' type='text/css' href='<%= request.getContextPath()%>/pub/fullcalendar/demos/cupertino/theme.css' />
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath()%>/pub/fullcalendar/fullcalendar/fullcalendar.css' />
<link rel='stylesheet' type='text/css' href='<%= request.getContextPath()%>/pub/fullcalendar/fullcalendar/fullcalendar.print.css' media='print' />
<script type='text/javascript' src='<%= request.getContextPath()%>/pub/fullcalendar/jquery/jquery-1.5.2.min.js'></script>
<script type='text/javascript' src='<%= request.getContextPath()%>/pub/fullcalendar/jquery/jquery-ui-1.8.11.custom.min.js'></script>
<script type='text/javascript' src='<%= request.getContextPath()%>/pub/fullcalendar/fullcalendar/fullcalendar.js'></script>

<script src="<%= request.getContextPath()%>/pub/calender/js/jquery.bubblepopup.v2.3.1.min.js" type="text/javascript"></script>
<%--<link href="<%= request.getContextPath()%>/pub/calender/css/document.css" rel="stylesheet" type="text/css" />--%>
<link href="<%= request.getContextPath()%>/pub/calender/css/jquery.bubblepopup.v2.3.1.css" rel="stylesheet" type="text/css" />
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>--%>

<script type="text/javascript">

    $(document).ready(function() {

        var titleArray = new Array();
        var pihakle = new Array();
        var name;
        var no = 0;
   
    <c:forEach items="${actionBean.listCalendar}" var="i" varStatus="j" >

            var startDate = new Date();
            startDate = '${i.tarikhMula}';
            no++;
            titleArray[no-1] = {title: '\n ${i.idMohon}  \n\n ENKUIRI DI ${i.tempat} \n\n',
                start: startDate,
        <%--description:'IDPermohonan : ${i.idMohon} <br> IDHakmilik :  <br> SIASATAN DI ${i.tempat} <br>',--%>
                    allDay: false};
    </c:forEach>



            $('#calendar').fullCalendar({

                theme: true,
                header: {
                    left: '',
                    center: 'prevYear prev title next nextYear',
                    right: 'today  month,agendaWeek,agendaDay'
                },
                editable : false,

                events: titleArray,

                dayClick: function(date, allDay, jsEvent, view) {
                    var newDate = append(date.getDate()) + "/" + append((date.getMonth()+1)) + "/" + date.getFullYear();
                    $('#datepicker', opener.document).val(newDate);
                    <c:forEach items="${actionBean.listCalendar}" var="i">
                    var startDate = new Date();
                    startDate = '${i.tarikhMula}';
                    var existDate = startDate.substring(8,10) + "/" + startDate.substring(5,7) + "/" + startDate.substring(0,4);
//                    if(newDate == existDate){
//                        alert("Sila pilih tarikh lain yang masih kosong.");
//                        $("#calendar").dialog("open");
//                    }
                    </c:forEach>
                    var hari = convertDay(newDate);
                    $('#hari', opener.document).val(hari);
                    self.close();
                    // change the day's background color just for fun
                    $(this).css('background-color', 'red');

                }
            });
        });

        function append(x) {return(x<0||x>9?"":"0")+x}

        function convertDay(value){
            var kodUrusan = ${actionBean.permohonan.kodUrusan.kod};
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
            var date1 = date.getDay();
            var today =  new Date();
<%--    <c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
            var hari = "";
            switch(date1){
                case 0:  hari = "Ahad";
                    break;
                case 1:  hari = "Isnin";
                    break;
                case 2:  hari = "Selasa";
                    break;
                case 3:  hari = "Rabu";
                    break;
                case 4:  hari = "Khamis";
                    break;
                case 5:  hari = "Jumaat";
                    break;
                case 6:  hari = "Sabtu";
                    break;
                default :hari = "salah";
                    break;
            }
            return hari;
    </c:if>--%>
            if (date < today){
                alert("Sila masukkan tarikh esok atau tarikh hari seterusnya.");
                $("#datepicker").val("");
                $('#hari').val("");
                $("#calendar").dialog("open");
            }else{
                var hari = "";
                switch(date1){
                    case 0:  hari = "Ahad";
                        break;
                    case 1:  hari = "Isnin";
                        break;
                    case 2:  hari = "Selasa";
                        break;
                    case 3:  hari = "Rabu";
                        break;
                    case 4:  hari = "Khamis";
                        break;
                    case 5:  hari = "Jumaat";
                        break;
                    case 6:  hari = "Sabtu";
                        break;
                    default :hari = "salah";
                        break;
                }
                return hari;
            }
        }
</script>
<style type="text/css">

    body {
        margin-top: 40px;
        text-align: center;
        font-size: 13px;
        font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
    }

    #calendar {
        width: 900px;
        margin: 0 auto;

    }

</style>

<div id="calendar"></div>
<c:if test="${actionBean.permohonan.kodUrusan.kod eq '351'}">
    <s:form beanclass="etanah.view.penguatkuasaan.EnkuiriKehadiranActionBean">
        <div class="content" align="centre">
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </div>
    </s:form>
</c:if>

<c:if test="${actionBean.permohonan.kodUrusan.kod ne '351'}">
    <s:form beanclass="etanah.view.penguatkuasaan.MaklumatLaporanEnkuiriActionBean">
        <div class="content" align="centre">
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </div>
    </s:form>
</c:if>


