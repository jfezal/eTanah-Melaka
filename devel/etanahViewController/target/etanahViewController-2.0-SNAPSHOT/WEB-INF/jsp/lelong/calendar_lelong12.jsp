<%-- 
    Document   : calendar_lelong12
    Created on : 12 Julai 2013, 3:03:46 PM
    Author     : Mazurahayati
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
        <c:forEach items="${b.nama}" var="c">
            <c:if test="${c.jenis.kod ne 'PG'}" >
                    name = name + "Penggadai : ${c.pihak.nama}<br>";
            </c:if>
            <c:if test="${c.jenis.kod eq 'PG'}" >
                    name = name + "${c.jenis.nama} : ${c.pihak.nama}<br>";
            </c:if>
        </c:forEach>
                pihakle[${m.index}] = name;
    </c:forEach>
    <c:forEach items="${actionBean.listCalender}" var="i" varStatus="j" >

            var startDate = new Date();
            startDate = '${i.tarikh}';
            no++;
            titleArray[no-1] = {title: '\n ${i.idMohon}  \n\n SIASATAN DI ${i.tempat} \n\n SIASATAN KE : ${i.bil} \n\n',
                start: startDate,
                description:'IDPermohonan : ${i.idMohon} <br> IDHakmilik : ${i.hakmilik} <br> SIASATAN DI ${i.tempat} <br> SIASATAN KE : ${i.bil} <br>'+pihakle[${j.index}],
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
                pihakle2[${n.index}] = name;
    </c:forEach>

    <c:forEach items="${actionBean.listCalender2}" var="k" varStatus="l" >

            var startDate = new Date();
            startDate = '${k.tarikh}';
            no++;
            titleArray[no-1] = {title: '\n ${k.idMohon}  \n\n LELONGAN DI ${k.tempat} \n\n LELONGAN KE : ${k.bil} \n\n',
                start: startDate,
                description:'IDPermohonan : ${k.idMohon} <br> IDHakmilik : ${k.hakmilik} <br> LELONGAN DI ${k.tempat} <br> LELONGAN KE : ${k.bil} <br>'+pihakle2[${l.index}],
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
                    var dE = '${actionBean.tarikhEnkuiri}';
                    var dateEn = dateValidation2(dE);
                    if(dateEn > date){
                        var sdate = append(dateEn.getUTCDate()) + "/" + append((dateEn.getUTCMonth()+1)) + "/" + dateEn.getFullYear();
                        alert("Tarikh Dimasukkan Salah. Sila Pilih Tarikh Selepas Daripada "+sdate);
                    }else{
                        var newDate = append(date.getDate()) + "/" + append((date.getMonth()+1)) + "/" + date.getFullYear();
                        $('#datepicker1', opener.document).val(newDate);
                        var hari = convertDay(newDate);
                        $('#hari1', opener.document).val(hari);
                        var bayar = dateValidation(newDate);
                        $('#tarikhBayar1', opener.document).val(bayar);
                        self.close();
                        // change the day's background color just for fun
                        $(this).css('background-color', 'red');
                    }
                },

                eventMouseover: function(event,calEvent,jsEvent){

                    $(this).CreateBubblePopup({
                        selectable: true,
                        position : 'top',
                        align	 : 'center',
                        innerHtml: event.description,
                        innerHtmlStyle: {
                            color:'#FFFFFF',
                            'text-align':'center'
                        },
                        themeName:'all-black',
                        themePath:'<%= request.getContextPath()%>/pub/calender/images/jquerybubblepopup-theme'
                    });
                },

                eventMouseout: function(event,calEvent,jsEvent){

                    $(this).RemoveBubblePopup();
                }
            });
        });

        function append(x) {return(x<0||x>9?"":"0")+x}

        function convertDay(value){
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
            var date1 = date.getDay();
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

        function dateValidation(value){
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
            var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+121)+'/'+(date.getFullYear());//+1900);
            var dateNew = new Date(date1);
            var sdate = append(dateNew.getUTCDate()) + "/" + append((dateNew.getUTCMonth()+1)) + "/" + dateNew.getFullYear();

            return sdate;
        }

        function dateValidation2(value){
            var vsplit = value.split('/');
            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];
            var date = new Date(fulldate);
            var date1 = (date.getUTCMonth()+1)+'/'+(date.getUTCDate()+33)+'/'+(date.getFullYear());//+1900);
            var dateNew = new Date(date1);

            return dateNew;
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
<s:form beanclass="etanah.view.stripes.lelong.UtilitiKemasukanPerintahMahkamahActionBean">
    <div class="content" align="centre">
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
    </div>
</s:form>














