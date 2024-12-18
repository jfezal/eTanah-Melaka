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
        $(document).ready( function() {


            var len = $(".daerah").length;

            for (var i=0; i<=len; i++){
                $('.hakmilik'+i).click( function() {
                    window.open("${pageContext.request.contextPath}/pengambilan/maklumat_hakmilikpengambilan?popup&idHakmilik="+$(this).text(), "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=890,height=600").focus();
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

</script>

<s:form beanclass="etanah.view.stripes.pengambilan.share.common.MaklumatHakmilikActionBean">
    <s:messages/>
    <div class="subtitle displaytag">

        <fieldset class="aras1" id="locate">
            <legend>
                Maklumat Tanah

            </legend>
                <p align="center">
                    <label><font color="black">Jumlah Keluasan Tanah : </font><font color=" blue">&nbsp;mp&nbsp;&nbsp;(<fmt:formatNumber pattern="#,##0.0000" value=""/>&nbsp;&nbsp;hektar)</font></label>&nbsp;
                </p>
                <%--<p>
                    <label>Meter Persegi :</label>${actionBean.amountMH}
                </p>
                <p>
                    <label>Hektar :</label><fmt:formatNumber maxFractionDigits="3" value="${actionBean.convH}"/>
                </p>--%>
                <p align="center">

                    <display:table class="tablecloth" name="${actionBean.listHakmilikDiambil}" pagesize="30" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/maklumat_hakmilik" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                     
                            <display:column title="ID Hakmilik" class="popup hakmilik${line_rowNum}">${line.idHakmilik}</display:column>
                            <display:column title="No Lot/No PT" >${line.noLotPt}</display:column>
                            <display:column title="Luas">
                                <fmt:formatNumber pattern="#,##0.0000" value="${line.luas}"/>&nbsp;
                                <c:if test="${line.luas eq 'Meter Persegi'}">mp</c:if>
                                <c:if test="${line.luas eq 'Hektar'}">Ha</c:if>
                            </display:column>
                            
                            <display:column title="Daerah" class="daerah">${line.daerah}</display:column>
                            <display:column title="Bandar/Pekan/Mukim">${line.mukim}</display:column>
                            <display:column title="Luas Diambil">
                                   
                                            <s:text name="luasAmbil[${line_rowNum - 1}]" onkeyup="validateLuas(this,${line_rowNum - 1})" onkeydown="return MaskMoney(this, event);"  formatPattern="#,##0.0000" id="luasTerlibat${line_rowNum - 1}" value="0"/>&nbsp;
                                            
                                </display:column>
                     
                        <%--hakmilik.kegunaanTanah.nama--%>
                        <display:column property="kegunaanTanah" title="Kegunaan Tanah" />
                        <display:column  title="Jumlah Pihak Kepentingan Berdaftar " class="popup hakmilik${line_rowNum}">${line.jumPihak}</display:column>
                </display:table>
                <br>                
                <s:button name="simpanHakmilik" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

            
        </fieldset>

    </div>

</s:form>
