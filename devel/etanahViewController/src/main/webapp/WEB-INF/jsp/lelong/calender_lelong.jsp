<%-- 
    Document   : calender_lelong
    Created on : Jul 21, 2010, 2:04:00 PM
    Author     : mdizzat.mashrom
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
    <c:forEach items="${actionBean.listCalender}" var="b"  varStatus="m">
        name = "";
        <c:if test="${b ne null}" >
            //alert("MASUK IF>>>>>");
            <c:forEach items="${b.nama}" var="c">
                //alert("MASUK FOR>>>>>");
                <c:if test="${c.jenis.kod ne 'PG'}" >
                   // alert("MASUK IF NE>>>>>");
        name = name + "Penggadai : <br>";
                </c:if>
                <c:if test="${c.jenis.kod eq 'PG'}" >
                    //alert("MASUK IF EQ>>>>>");
                        name = name + "${c.jenis.nama} : <br>";
                </c:if>
            </c:forEach>
        </c:if>
                pihakle[${m.index}] = name;
    </c:forEach>
    <c:forEach items="${actionBean.listCalender}" var="i" varStatus="j">
//            alert("i.idMohon = ");
            var startDate = new Date('${i.tarikh}');
          
//            startDate = '${i.tarikh}';
            no++;
            titleArray[no - 1] = {title: '\n ${i.idMohon} \n\n SIASATAN KE : ${i.bil} \n\n',
                start: startDate,
                description: 'IDPermohonan : ${i.idMohon} <br> IDHakmilik : ${i.hakmilik} <br> SIASATAN KE : ${i.bil} <br>' + pihakle[${j.index}],
                allDay: false};
    </c:forEach>
            var pihakle2 = new Array();
            var name2;
    <c:forEach items="${actionBean.listCalender2}" var="x"  varStatus="n">
            name2 = "";
        <c:forEach items="${x.nama}" var="z">
            <c:if test="${z.jenis.kod ne 'PG'}" >
            name2 = name2 + "Penggadai : ${z.pihak.nama}<br>";
            </c:if>
            <c:if test="${z.jenis.kod eq 'PG'}" >
                    name2 = name2 + "${z.jenis.nama} : ${z.pihak.nama}<br>";
            </c:if>
        </c:forEach>
                pihakle2[${n.index}] = name2;
    </c:forEach>
    <c:forEach items="${actionBean.listCalender2}" var="k" varStatus="l" >

            var startDate = new Date();
            startDate = '${k.tarikh}';
            no++;
          
            titleArray[no - 1] = {title: '\n ${k.idMohon} \n\n LELONGAN KE : ${k.bil} \n\n',
                start: startDate,
                description: 'IDPermohonan : ${k.idMohon} <br> IDHakmilik : ${k.hakmilik} <br> LELONGAN KE : ${k.bil} <br>' + pihakle2[${l.index}],
                allDay: false};
    </c:forEach>

            $('#calendar').fullCalendar({
                theme: true,
                header: {
                    left: '',
                    center: 'prevYear prev title next nextYear',
                    right: 'today  month,agendaWeek,agendaDay'
                },
                editable: false,
                events: titleArray,
                dayClick: function(date, allDay, jsEvent, view) {
                    var dE = '${actionBean.tarikhPermohonan}';
                    var vsplit = dE.split('/');
                    var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
                    var dateNow = new Date(fulldate);
                    if (date <= dateNow) {
                        alert("Tarikh Yang Dimasukkan Salah.Sila Pilih Tarikh Melebihi Dari Tarikh Masuk Permohonan : " + dE);
                    } else {
                        var newDate = append(date.getDate()) + "/" + append((date.getMonth() + 1)) + "/" + date.getFullYear();
                        $('#datepicker', opener.document).val(newDate);
                        var hari = convertDay(newDate);
                        $('#hari', opener.document).val(hari);
                        self.close();
                        // change the day's background color just for fun
                        $(this).css('background-color', 'red');
                    }
                },
                eventMouseover: function(event, calEvent, jsEvent) {

                    $(this).CreateBubblePopup({
                        selectable: false,
                        position: 'top',
                        align: 'center',
                        innerHtml: event.description,
                        innerHtmlStyle: {
                            color: '#FFFFFF',
                            'text-align': 'center'
                        },
                        themeName: 'all-black',
                        themePath: '<%= request.getContextPath()%>/pub/calender/images/jquerybubblepopup-theme'
                    });
                },
                eventMouseout: function(event, calEvent, jsEvent) {

                    $(this).RemoveBubblePopup();
                }

            });
        });

        function append(x) {
            return(x < 0 || x > 9 ? "" : "0") + x
        }

        function convertDay(value) {
            var vsplit = value.split('/');
            var fulldate = vsplit[1] + '/' + vsplit[0] + '/' + vsplit[2];
            var date = new Date(fulldate);
            var date1 = date.getDay();
            var hari = "";
            switch (date1) {
                case 0:
                    hari = "Ahad";
                    break;
                case 1:
                    hari = "Isnin";
                    break;
                case 2:
                    hari = "Selasa";
                    break;
                case 3:
                    hari = "Rabu";
                    break;
                case 4:
                    hari = "Khamis";
                    break;
                case 5:
                    hari = "Jumaat";
                    break;
                case 6:
                    hari = "Sabtu";
                    break;
                default :
                    hari = "salah";
                    break;
            }
            return hari;
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
        width: 1100px;
        margin: 0 auto;

    }

</style>

<div id="calendar"></div>
<s:form beanclass="etanah.view.stripes.lelong.MaklumatEnkuiriActionBean">
    <div class="content" align="centre">
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </div>
</s:form>

