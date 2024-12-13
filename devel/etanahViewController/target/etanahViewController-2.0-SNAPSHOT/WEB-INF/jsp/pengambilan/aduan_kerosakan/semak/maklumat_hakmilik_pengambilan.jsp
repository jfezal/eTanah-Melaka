<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<%
            DecimalFormat dcf = new DecimalFormat("#0.0000");
%>
<script type="text/javascript">
    function removeSingle(id)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?deleteSingle&id='
                +id;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    <%-- alert(ialertd);--%>
        }
        function tambahBaru(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?hakMilikPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function refreshPageHakmilik(){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }

        $(document).ready( function() {


            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600");
                });
            }
        });

        function validateLuas(idVar,rowNo){
       
            var str = idVar.value;
            var luasTerlibat= parseFloat(idVar.value)
            //alert('LuasTerlibat Id: '+ luasTerlibat);
            
            var luas = parseFloat($('#luas'+rowNo).val());
            //var luas = parseFloat(document.getElementById("luas"+rowNo).value);
            //alert('Luas Id: '+ luas);
            //alert(luas);
            // var luas = parseInt(document.getElementById("luas"+rowNo).value);
            //  var luas = parseInt(document.getElementById("luas"+rowNo).value);
            //  alert('Luas'+luas);

            if(luasTerlibat > luas){
                alert("Pastikan Luas Diambil tidak melebihi Luas");
                idVar.value = str.substring(0,str.length-1);
                idVar.focus();
                return true;
            }
        }

        function MaskMoney(obj, evt) {
            var parts = obj.value.split('.');

            if (!((evt.keyCode == 190) ||
                (evt.keyCode == 8) ||
                (evt.keyCode == 188) ||
                (evt.keyCode == 144) ||
                (evt.keyCode == 96) ||
                (evt.keyCode == 97) ||
                (evt.keyCode == 98) ||
                (evt.keyCode == 99) ||
                (evt.keyCode == 100) ||
                (evt.keyCode == 101) ||
                (evt.keyCode == 102) ||
                (evt.keyCode == 103) ||
                (evt.keyCode == 104) ||
                (evt.keyCode == 105) ||
                (evt.keyCode == 110) ||
                (evt.keyCode >= 48 && evt.keyCode <= 57))) return false;

            var parts = obj.value.split('.');
            if (parts.length > 2) return false;
            if (evt.keyCode == 46) return (parts.length == 1);
            if (evt.keyCode ==  8) return (parts.length  > 0);
            if (parts[0].length >= 14) return false;
            if (parts.length == 2 && parts[1].length >= 4) return false;
        }

        function validateKodUnitLuas(idVar,rowNo){
    <%--alert(idVar.value);--%>
            if(idVar.value == 'M'){
                var unitLuasDiambil = "mp";
                alert(unitLuasDiambil);
            }
            if(idVar.value == 'H'){
                var unitLuasDiambil = "Ha";
                alert(unitLuasDiambil);
            }

            var unitLuas = document.getElementById("unitLuas"+rowNo).value;
            alert(unitLuas);

        }

        function save(event, f){
            var q = $(f).formSerialize();
            var url = f.action + '?' + event;
            $.post(url,q,
            function(data){
                $('#page_div',opener.document).html(data);
            
            },'html');

        }

        function tambahTanahKR(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?tanahKRPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        function tambahTanahTDK(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?tanahTDKPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }
        
        function tambahTanahAA(){
            window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?tanahAAPopup", "eTanah",
            "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

        function popupTanahRizab(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?showEdittanahKR&idTanahRizabPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function popupTanahAA(h){
            var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?showEdittanahAA&idTanahRizabPermohonan='+h;
            window.open(url, "eTanah","status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }
        function removeTanahRizab(idTanahRizabPermohonan)
        {
            if(confirm('Adakah anda pasti?')) {
                var url = '${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?deleteTanahRizab&idTanahRizabPermohonan='
                    +idTanahRizabPermohonan;
                $.get(url,
                function(data){
                    $('#page_div').html(data);
                    self.opener.refreshPageHakmilik();
                },'html');
            }
        }
        $(document).ready(function() {
            var bil =  ${fn:length(actionBean.hakmilikPermohonanList)};
            for (var i = 0; i < bil; i++){
                if($("#desak"+i).val() == "1"){
                    document.getElementById("pegangan"+i).checked = true;
                  
                }
            }
        });


</script>

<s:form beanclass="etanah.view.stripes.pengambilan.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah Aduan

            </legend>
            <c:if test="${edit}">
                <p align="center">
                    <label><font color="black">Jumlah Keluasan Tanah : </font><font color=" blue">${actionBean.amountMH}&nbsp;mp&nbsp;&nbsp;(<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.convH}"/>&nbsp;&nbsp;hektar)</font></label>&nbsp;
                </p>
                <%--<p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>--%>
                <p align="center">

                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.hakmilik.idHakmilik}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="ID Hakmilik">-</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="No Lot/No PT" >${line.lot.nama}${line.noLot}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Luas">
                                <s:hidden formatPattern="#0.0000" name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="Luas">
                                <s:hidden formatPattern="#0.0000" name="luas1" value="${line.luasBaru}" id="luas${line_rowNum-1}" />
                                <s:hidden name="unitLuas" value="${line.kodUnitLuasBaru.nama}" id="unitLuas${line_rowNum-1}" />
                                <fmt:formatNumber pattern="#,##0.0000" value="${line.luasBaru}"/>&nbsp;
                                <c:if test="${line.kodUnitLuasBaru.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.kodUnitLuasBaru.nama eq 'Hektar'}">Ha</c:if>
                            </display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Daerah" class="daerah">${line.hakmilik.daerah.nama}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}"> 
                            <display:column title="Daerah" class="daerah">${actionBean.kodDaerah}</display:column>
                        </c:if>
                        <c:if test="${line.hakmilik ne null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.hakmilik.bandarPekanMukim.nama} ${line.hakmilik.seksyen.nama}</display:column><%--${fn:toLowerCase}--%>
                        </c:if>
                        <c:if test="${line.hakmilik eq null}">
                            <display:column title="Bandar/Pekan/Mukim">${line.bandarPekanMukimBaru.nama} ${line.kodSeksyen.nama}</display:column>
                        </c:if>
                                
                        <%--
                        <c:choose>
                            <c:when test="${line.permohonan.kodUrusan.kod eq 'PHLLP'||line.permohonan.kodUrusan.kod eq 'PHLLA'||line.permohonan.kodUrusan.kod eq 'PHL'||line.permohonan.kodUrusan.kod eq 'PTNB'||line.permohonan.kodUrusan.kod eq 'PILDA'}">
                                <display:column title="Luas Diperlukan">
                                    <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                    <c:if test="${line.luasTerlibat ne null}">
                                        <%--onkeyup="validateLuas(this,${line_rowNum - 1})"--%><%--
                                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" onkeyup="validateLuas(this,${line_rowNum - 1})" formatPattern="#,##0.0000"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                </display:column>
                            </c:when>
                            <c:otherwise>
                                <display:column title="Luas Diambil">
                                    <%--onkeyup="validateLuas(this,${line_rowNum - 1})"--%><%--
                                    <c:if test="${line.hakmilik ne null}">
                                        <c:if test="${line.luasTerlibat eq null}">
                                            <s:text name="luasTerlibat[${line_rowNum - 1}]" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}" value="0"/>&nbsp;
                                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                        </c:if>
                                        <c:if test="${line.luasTerlibat ne null}">
                                            <%--onkeyup="validateLuas(this,${line_rowNum - 1})"--%> <%--
                                            <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000"/>&nbsp;
                                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${line.hakmilik eq null}">
                                        <c:if test="${line.luasTerlibat eq null}">
                                            <s:text name="luasTerlibat[${line_rowNum - 1}]" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}" value="0"/>&nbsp;
                                            <c:if test="${line.kodUnitLuasBaru.nama eq 'Meter Persegi'}">mp</c:if>
                                            <c:if test="${line.kodUnitLuasBaru.nama eq 'Hektar'}">Ha</c:if>
                                        </c:if>
                                        <c:if test="${line.luasTerlibat ne null}">
                                            <%--onkeyup="validateLuas(this,${line_rowNum - 1})"--%> <%--
                                            <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;
                                            <c:if test="${line.kodUnitLuasBaru.nama eq 'Meter Persegi'}">mp</c:if>
                                            <c:if test="${line.kodUnitLuasBaru.nama eq 'Hektar'}">Ha</c:if>
                                        </c:if>

                                        <%--<s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000"/>&nbsp;
                                        <c:if test="${line.kodUnitLuasBaru.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.kodUnitLuasBaru.nama eq 'Hektar'}">Ha</c:if>--%><%--
                                    </c:if>
                                </display:column>
                            </c:otherwise>
                            
                            
                            
                        </c:choose> 
                        --%>
                        
                        <%--
                        <display:column title="Baki Luas">
                            <c:if test="${line.hakmilik ne null}">
                                <c:if test="${line.luasTerlibat ne null}">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.hakmilik.luas}" var="b"/>
                                    <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                                </c:if>
                                <c:if test="${line.luasTerlibat eq null}">0</c:if>
                            </c:if>
                            <c:if test="${line.hakmilik eq null}">
                                <c:if test="${line.luasBaru ne null}">
                                    <c:set value="${line.luasTerlibat}" var="a"/>
                                    <c:set value="${line.luasBaru}" var="b"/>
                                    <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                                </c:if>
                                <c:if test="${line.luasBaru eq null}">0</c:if>
                            </c:if>
                        </display:column>
                        
                        --%>
                        <%--hakmilik.kegunaanTanah.nama--%>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />
                        <c:if test="${!edit}">
                            <display:column title="Hapus">
                            <div align="center">
                                <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                     id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                            </div>
                        </display:column>
                    </c:if>
                </display:table>
                <br>
                <%-- Sebahagian
                  <display:table class="tablecloth" name="${actionBean.sebahagianList}" pagesize="20" cellpadding="0" cellspacing="0"
                                    requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                         <display:column title="No" sortable="true">${line_rowNum}</display:column>
                         <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                         <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                         <display:column title="Luas">
                             <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                             <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                             <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                         </display:column>
                         <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                         <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                         <c:choose>
                             <c:when test="${line.permohonan.kodUrusan.kod eq 'PHLLP'||line.permohonan.kodUrusan.kod eq 'PHLLA'||line.permohonan.kodUrusan.kod eq 'PHL'||line.permohonan.kodUrusan.kod eq 'PTNB'||line.permohonan.kodUrusan.kod eq 'PILDA'}">
                                     <display:column title="Luas Diperlukan">
                                         <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                         </c:if>
                                         <c:if test="${line.luasTerlibat ne null}">
                                             <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})"/>&nbsp;
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                         </c:if>
                                     </display:column>
                             </c:when>
                             <c:otherwise>
                                 <display:column title="Luas Diambil">
                                     <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                     </c:if>
                                     <c:if test="${line.luasTerlibat ne null}">
                                         <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})"/>&nbsp;
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                     </c:if>
                                 </display:column>
                             </c:otherwise>
                         </c:choose>
                         <display:column title="Baki Luas">
                             <c:if test="${line.luasTerlibat ne null}">
                                 <c:set value="${line.luasTerlibat}" var="a"/>
                                 <c:set value="${line.hakmilik.luas}" var="b"/>
                                 <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                             </c:if>
                             <c:if test="${line.luasTerlibat eq null}">0</c:if>
                         </display:column>
                         <display:column property="hakmilik.kegunaanTanah.nama" title="Kegunaan Tanah" />
                         <c:if test="${!edit}">
                             <display:column title="Hapus">
                             <div align="center">
                                 <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                      id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                             </div>
                         </display:column>
                     </c:if>
                 </display:table>
                             <br>
                             Keseluruhan
                              <display:table class="tablecloth" name="${actionBean.keseluruhanList}" pagesize="20" cellpadding="0" cellspacing="0"
                                    requestURI="/pengambilan/maklumat_hakmilikpengambilan" id="line">
                         <display:column title="No" sortable="true">${line_rowNum}</display:column>
                         <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                         <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                         <display:column title="Luas">
                             <s:hidden name="luas1" value="${line.hakmilik.luas}" id="luas${line_rowNum-1}" />
                             <s:hidden name="unitLuas" value="${line.hakmilik.kodUnitLuas.nama}" id="unitLuas${line_rowNum-1}" />
                             <fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                         </display:column>
                         <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                         <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                         <c:choose>
                             <c:when test="${line.permohonan.kodUrusan.kod eq 'PHLLP'||line.permohonan.kodUrusan.kod eq 'PHLLA'||line.permohonan.kodUrusan.kod eq 'PHL'||line.permohonan.kodUrusan.kod eq 'PTNB'||line.permohonan.kodUrusan.kod eq 'PILDA'}">
                                     <display:column title="Luas Diperlukan">
                                         <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                         </c:if>
                                         <c:if test="${line.luasTerlibat ne null}">
                                             <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})"/>&nbsp;
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                             <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                         </c:if>
                                     </display:column>
                             </c:when>
                             <c:otherwise>
                                 <display:column title="Luas Diambil">
                                     <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                     </c:if>
                                     <c:if test="${line.luasTerlibat ne null}">
                                         <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})"/>&nbsp;
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                         <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                     </c:if>
                                 </display:column>
                             </c:otherwise>
                         </c:choose>
                         <display:column title="Baki Luas">
                             <c:if test="${line.luasTerlibat ne null}">
                                 <c:set value="${line.luasTerlibat}" var="a"/>
                                 <c:set value="${line.hakmilik.luas}" var="b"/>
                                 <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>
                             </c:if>
                             <c:if test="${line.luasTerlibat eq null}">0</c:if>
                         </display:column>
                         <display:column property="hakmilik.kegunaanTanah.nama" title="Kegunaan Tanah" />
                         <c:if test="${!edit}">
                             <display:column title="Hapus">
                             <div align="center">
                                 <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                      id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.hakmilikPermohonanList[line_rowNum-1].id}');" />
                             </div>
                         </display:column>
                     </c:if>
                 </display:table>--%>
                 <%--
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahBaru();"/>&nbsp;
               
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                --%>

            </c:if>
            <c:if test="${!edit}">
                <%--<p align="center">
                    <label>Jumlah Keluasan Tanah</label>&nbsp;
                </p>
                <p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label>${actionBean.convH}
                </p>--%>
                <p align="center">
                    <label><font color="black">Jumlah Keluasan Tanah : </font><font color=" blue">${actionBean.amountMH}&nbsp;mp&nbsp;&nbsp;(<fmt:formatNumber pattern="#,##0.0000" value="${actionBean.convH}"/>&nbsp;&nbsp;hektar)</font></label>&nbsp;
                </p>
                <br>
                <p align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_asas_pengambilan" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" class="popup hakmilik${line_rowNum}"/>
                        <%--<display:column title="No. Hakmilik"  class="popup hakmilik${line_rowNum}">${line.hakmilik.kodHakmilik.kod}${line.hakmilik.noHakmilik}</display:column>--%>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                            <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                        </display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column title="Bandar/Pekan/Mukim" style="text-transform : capitalize">${line.hakmilik.bandarPekanMukim.nama} ${fn:toLowerCase(line.hakmilik.seksyen.nama)}</display:column>
                        <c:choose>
                            <c:when test="${line.permohonan.kodUrusan.kod eq 'PHLLP'||line.permohonan.kodUrusan.kod eq 'PHLLA'||line.permohonan.kodUrusan.kod eq 'PHL'}">
                                <display:column title="Luas Diperlukan">
                                    <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000"  onkeyup="validateLuas(this,${line_rowNum - 1})" value="0"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                    <c:if test="${line.luasTerlibat ne null}">
                                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                </display:column>
                            </c:when>
                            <c:otherwise>
                                <display:column title="Luas Diambil">
                                    <c:if test="${line.luasTerlibat eq null}"><s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);" value="0"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                    <c:if test="${line.luasTerlibat ne null}">
                                        <s:text name="luasTerlibat[${line_rowNum - 1}]" id="luasTerlibat${line_rowNum - 1}" formatPattern="#,##0.0000" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"/>&nbsp;
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                        <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                                    </c:if>
                                </display:column>
                            </c:otherwise>
                        </c:choose>
                        <display:column title="Baki Luas">
                            <c:if test="${line.luasTerlibat ne null}">
                                <c:set value="${line.luasTerlibat}" var="a" />
                                <c:set value="${line.hakmilik.luas}" var="b"/>
                                <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>&nbsp;
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.hakmilik.kodUnitLuas.nama eq 'Hektar'}">Ha</c:if>
                            </c:if>
                            <c:if test="${line.luasTerlibat eq null}">0</c:if>
                        </display:column>
                        <%--hakmilik.kegunaanTanah.nama--%>
                        <display:column property="hakmilik.syaratNyata.syarat" title="Kegunaan Tanah" />


                        <display:column title="Kedesakan">
                        <div align="center">
                            <s:checkbox  name="pegangan${line_rowNum - 1}"  id="pegangan${line_rowNum-1}" value="${line.pegangan}" disabled="true" />
                            <s:hidden name="desak[${line_rowNum-1}]" value="${line.pegangan}" id="desak${line_rowNum-1}"/>


                        </div>
                    </display:column>
                </display:table>
            </c:if>
        </fieldset>
        <%--<c:if test="${actionBean.permohonan.kodUrusan.kod eq '831A'}">


 <fieldset class="aras1" id="locate">
    <legend>
        Maklumat Tanah Rizab
    </legend>
    <p align="center">
    <c:if test="${edit}">
    <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                   requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
        <display:column title="No">${line_rowNum}</display:column>
        <display:column property="rizab.nama" title="Jenis Rizab" />
        <display:column property="cawangan.name" title="Cawangan" />
        <display:column property="daerah.nama" title="Daerah" />
        <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
        <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.0000" value="${line.luasDiambil}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
        <display:column title="Baki Luas">
            <c:if test="${line.luasTerlibat ne null}">
                  <c:set value="${line.luasDiambil}" var="a"/>
                  <c:set value="${line.luasTerlibat}" var="b"/>
                  <fmt:formatNumber pattern="#,##0.0000" value="${b-a}"/>&nbsp;${line.kodUnitLuas.nama}
            </c:if>
            <c:if test="${line.luasDiambil eq null}">0</c:if>
        </display:column>
        <display:column property="noLot" title="No. PT/Lot"/>
        <display:column property="noWarta" title="No. Warta"/>
        <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
        <display:column property="namaPenjaga" title="Pegawai Pengawal"/>
         <display:column title="Kemaskini">
        <div align="center">
            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popupTanahRizab('${line.idTanahRizabPermohonan}');"/>
        </div>
        </display:column>
        <display:column title="Hapus">
        <div align="center">
            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                 id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeTanahRizab('${line.idTanahRizabPermohonan}');" />
        </div>
        </display:column>
    </display:table>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="tambahTanahKR();"/>&nbsp;

            </c:if>
            <c:if test="${!edit}">
                 <display:table class="tablecloth" name="${actionBean.tanahRizabList}" pagesize="20" style="width:100%" cellpadding="0" cellspacing="0" id="line"
                               requestURI="${pageContext.request.contextPath}/maklumat_tambahan">
                    <display:column title="No">${line_rowNum}</display:column>
                    <display:column property="rizab.nama" title="Jenis Rizab" />
                    <display:column property="cawangan.name" title="Cawangan" />
                    <display:column property="daerah.nama" title="Daerah" />
                    <display:column property="bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                    <display:column title="Luas"><fmt:formatNumber pattern="#,##0.00" value="${line.luasTerlibat}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Luas Diambil"><fmt:formatNumber pattern="#,##0.00" value="${line.luasDiambil}"/>&nbsp;${line.kodUnitLuas.nama}</display:column>
                    <display:column title="Baki Luas">
                        <c:if test="${line.luasTerlibat ne null}">
                              <c:set value="${line.luasDiambil}" var="a"/>
                              <c:set value="${line.luasTerlibat}" var="b"/>
                              <fmt:formatNumber pattern="#,##0.00" value="${b-a}"/>&nbsp;${line.kodUnitLuas.nama}
                        </c:if>
                        <c:if test="${line.luasDiambil eq null}">0</c:if>
                    </display:column>
                    <display:column property="noLot" title="No. PT/Lot"/>
                    <display:column property="noWarta" title="No. Warta"/>
                    <display:column property="tarikhWarta" title="Tarikh Warta" format="{0,date,dd/MM/yyyy}"/>
                    <display:column property="namaPenjaga" title="Pegawai Pengawal"/>
                </display:table>
            </c:if>
        </fieldset>



                    </c:if>--%>

    </div>

</s:form>
