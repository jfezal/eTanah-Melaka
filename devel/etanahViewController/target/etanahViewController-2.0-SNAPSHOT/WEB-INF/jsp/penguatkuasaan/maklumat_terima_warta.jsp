<%--
    Document   : maklumat_terima_warta
    Created on : June 03, 2011, 011:59:00 AM
    Author     : latifah.iskak
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<%-- <script type="text/javascript"
       src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>--%>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="${pageContext.request.contextPath}/styles/styles.css" />
        <title>Terimaan Warta</title>

    </head>
    <body>
        <script type="text/javascript">
            $(document).ready( function(){
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

                var bil =  ${fn:length(actionBean.senaraiWarta)};
                
            <c:choose>
                <c:when test="${actionBean.kodNegeri eq '05'}">
                    <c:choose>
                        <c:when test="${actionBean.permohonan.kodUrusan.kod eq '351' || actionBean.permohonan.kodUrusan.kod eq '352'}">
                                var currentTime = new Date();
                                var month = currentTime.getMonth() + 1;
                                var day = currentTime.getDate();
                                var year = currentTime.getFullYear();
                                var todayDate = day + "/" + month + "/" + year;
                            <%--alert(todayDate);--%>
                                for (var i = 0; i < bil; i++){
                                    var tarikh = document.getElementById("tarikhSampai"+i).value;

                                    var vsplit1 = todayDate.split('/');
                                    var fulldate1 = vsplit1[1]+'/'+vsplit1[0]+'/'+vsplit1[2];

                                    var vsplit2 = tarikh.split('/');
                                    var fulldate2 = vsplit2[1]+'/'+vsplit2[0]+'/'+vsplit2[2];

                                    var sdate1 = new Date(fulldate1);
                                    var sdate2 = new Date(fulldate2);

                                    var m = sdate1 - sdate2;
                                    var day = 365 - (m/(1000*60*60))/24;
                                    var x = Math.floor(parseFloat(day));
                            <%--alert(x);--%>
                                    if(x > 0){
                                        $('#displayDays'+i).append(x);
                                    }else{
                                        $('#displayDays'+i).append('0');
                                    }
                                }
                        </c:when>
                        <c:otherwise>
                            for (var i = 0; i < bil; i++){
                                $('#displayDays'+i).append('0');
                            }                            
                        </c:otherwise>
                    </c:choose>                    
                </c:when>
                <c:otherwise>
                        for (var i = 0; i < bil; i++){
                            var tarikh = document.getElementById("tarikhSampai"+i).value;

                            var vsplit = tarikh.split('/');
                            var fulldate = vsplit[1]+'/'+vsplit[0]+'/'+vsplit[2];

                            var sdate = new Date(fulldate);
                            sdate.setDate(sdate.getDate() + 365); 
                        
                            var dd = sdate.getDate();
                            var mm = sdate.getMonth() + 1;
                            var y = sdate.getFullYear();

                            var someFormattedDate = dd + '/'+ mm + '/'+ y;

                            //                            alert(someFormattedDate);
                            $('#displayDays'+i).append(someFormattedDate);
                        }
                </c:otherwise>
            </c:choose>
                
                });


                function validate(){
                    return true;
                }

                function tambahWarta(){
                    window.open("${pageContext.request.contextPath}/penguatkuasaan/terima_warta?tambahWarta", "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=910,height=600,scrollbars=yes");
                }

                function refreshWarta(){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/terima_warta?refreshpage';
                    $.get(url, function(data){$('#page_div').html(data);},'html');
                }

                function viewWarta(id){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/terima_warta?viewTerimaanWarta&id='+id;
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=800,height=500,scrollbars=yes");
                }

                function removeWarta(id){
                    if(confirm('Adakah anda pasti untuk hapus?')) {
                        var url = '${pageContext.request.contextPath}/penguatkuasaan/terima_warta?deleteWarta&id='+id;
                        $.get(url,
                        function(data){
                            $('#page_div').html(data);
                        },'html');}
                }

                function editWarta(id){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/terima_warta?editWarta&id='+id;
                    window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800,scrollbars=yes");
                }


        </script>
        <s:errors />

        <s:form beanclass="etanah.view.penguatkuasaan.TerimaanWartaActionBean">
            <s:messages/>
            <s:errors/>

            <c:if test="${edit}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Terimaan Warta
                        </legend>
                        <div class="content" align="center">
                            <div class="content" align="left">
                                <p>Klik butang tambah untuk masukkan maklumat terimaan warta</p>
                            </div>

                            <display:table class="tablecloth" name="${actionBean.senaraiWarta}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Jenis Warta"><a class="popup" onclick="viewWarta(${line.idRujukan})">${line.kodRujukan.kod}</a></display:column>
                                <display:column property="noRujukan" title="No. Warta"></display:column>
                                <display:column title="Tarikh Warta" class="${line_rowNum}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhRujukan}"/> <br>
                                </display:column>
                                <display:column title="Tarikh Terima" class="${line_rowNum}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/> <br>
                                </display:column>
                                <c:if test="${actionBean.kodNegeri eq '05'}">
                                    <display:column title="Tempoh Pemantauan (Hari)">
                                        <div id="displayDays${line_rowNum-1}"></div>
                                        <s:hidden name="senaraiWarta[${line_rowNum-1}].tarikhDisampai" id="tarikhSampai${line_rowNum-1}" value="${line.tarikhDisampai}" formatType="Date" formatPattern="dd/MM/yyyy"/>
                                    </display:column>
                                </c:if>
                                <c:if test="${actionBean.kodNegeri eq '04'}">
                                    <display:column title="Tarikh Pemantauan">
                                        <div id="displayDays${line_rowNum-1}"></div>
                                        <s:hidden name="senaraiWarta[${line_rowNum-1}].tarikhDisampai" id="tarikhSampai${line_rowNum-1}" value="${line.tarikhDisampai}" formatType="Date" formatPattern="dd/MM/yyyy"/>
                                    </display:column>
                                </c:if>

                                <display:column title="Hapus">
                                    <div align="center">
                                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeWarta('${line.idRujukan}');"/>
                                    </div>
                                </display:column>

                                <display:column title="Kemaskini">
                                    <div align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                             id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="editWarta('${line.idRujukan}');"/>
                                    </div>
                                </display:column>

                            </display:table>
                            <c:choose>
                                <c:when test="${actionBean.kodNegeri eq '05'}">
                                    <c:if test="${fn:length(actionBean.senaraiWarta) eq 0 || actionBean.permohonan.kodUrusan.kod eq '127'}">
                                        <table>
                                            <tr>
                                                <td align="right">
                                                    <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahWarta();"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <table>
                                        <tr>
                                            <td align="right">
                                                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahWarta();"/>
                                            </td>
                                        </tr>
                                    </table>
                                </c:otherwise>
                            </c:choose>

                            <br>
                        </div>
                    </fieldset>
                </div>
            </c:if>


            <c:if test="${view}">
                <div class="subtitle">
                    <fieldset class="aras1">
                        <legend>
                            Maklumat Terimaan Warta
                        </legend>
                        <div class="content" align="center">
                            <display:table class="tablecloth" name="${actionBean.senaraiWarta}" cellpadding="0" cellspacing="0" id="line" >
                                <display:column title="Bil">${line_rowNum}</display:column>
                                <display:column title="Jenis Warta"><a class="popup" onclick="viewWarta(${line.idRujukan})">${line.kodRujukan.kod}</a></display:column>
                                <display:column property="noRujukan" title="No. Warta"></display:column>
                                <display:column title="Tarikh Warta" class="${line_rowNum}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhRujukan}"/> <br>
                                </display:column>
                                <display:column title="Tarikh Terima" class="${line_rowNum}">
                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${line.tarikhTerima}"/> <br>
                                </display:column>
                                <display:column title="Tempoh Pemantauan (Hari)">
                                    <div id="displayDays${line_rowNum-1}"></div>
                                    <s:hidden name="senaraiWarta[${line_rowNum-1}].tarikhDisampai" id="tarikhSampai${line_rowNum-1}" value="${line.tarikhDisampai}" formatType="Date" formatPattern="dd/MM/yyyy"/>
                                </display:column>
                            </display:table>
                            <br>
                        </div>
                    </fieldset>
                </div>
            </c:if>
        </s:form>

    </body>
</html>
