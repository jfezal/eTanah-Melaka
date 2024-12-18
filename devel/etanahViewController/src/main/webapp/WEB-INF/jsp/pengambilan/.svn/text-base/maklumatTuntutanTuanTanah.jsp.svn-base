<%--
    Document   : maklumatTuntutanTuanTanah
    Created on : Apr 27, 2010, 3:32:06 PM
    Author     : massita
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript">
   $(document).ready(function() {
        $('#senaraiTuntutanKos0').val('T');
        $('#amaun0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#amaunt0').val((${actionBean.jumCaraBayar}).toFixed(2));
        $('#jumCaraBayar').val((${actionBean.jumCaraBayar}).toFixed(2));
        var bil = parseInt(${actionBean.bil});
            var amnt = parseInt(${actionBean.jumCaraBayar});
            if(amnt <= 0){
                $('#next').attr("disabled", "true");
                $('#tble').attr("disabled", "true");
            }
        for (var i = 1; i < bil; i++){
            $('#amaunt'+i).attr("disabled", "true");
            $('#amaunt'+i).val("0.00");
            $('#amaun'+i).attr("disabled", "true");
            $('#amaun'+i).val("0");
            $("#field"+i).hide();
        }
    });
</script>
<script type="text/javascript">
function validateForm(){
    self.opener.refreshPageganti();
    self.close();
}
function test(f) {
        $(f).clearForm();
    }
function updateTotal (inputTxt)
    {
        var total = 0;
        var bil = ${actionBean.bil};
        for (var i = 0; i <bil; i++){
    	var a = document.getElementById('form1' + i)
            if (a == null) break;
            if ((isNaN(a.value))||((a.value) ==""))
            {
                alert("Nombor tidak sah");
                inputTxt.value = RemoveNonNumeric(a);
                $('#form1').val("0.00");
                return;
            }
            total += parseFloat(a.value);
        }
        var t = document.getElementById('form1');
        t.value = total.toFixed(2);
        inputTxt.value = parseFloat(inputTxt.value).toFixed(2);
    }


function RemoveNonNumeric( strString ){
        var strValidCharacters = "1234567890.";
        var strReturn = "0.00";
        var strBuffer = "0.00";
        var intIndex = 0;
        // Loop through the string
        for( intIndex = 0; intIndex < strString.length; intIndex++ )
        {
            strBuffer = strString.substr( intIndex, 1 );
            // Is this a number
            if( strValidCharacters.indexOf( strBuffer ) > -1 )
            {
                strReturn += strBuffer;
            }
        }
        return strReturn;
    }

     function removeSingle(idKos)
    {
        if(confirm('Adakah anda pasti?')) {
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?deleteSingle&idKos='
                +idKos;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');}
    }

    function addNew1(){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?popupTptd","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

      function addNew(){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?popup", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
        }

         function returnPage(){
        window.open("${pageContext.request.contextPath}/pengambilan/gantiRugi?popupReturn","eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=800");
    }

    function refreshPageganti(){
            var url = '${pageContext.request.contextPath}/pengambilan/gantiRugi?refreshpage';
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
</script>

<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil"/>
    <s:form beanclass="etanah.view.stripes.pengambilan.perbicaraanKosGantiRugiActionBean" id="form1">
        <s:messages/>
        <br />
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>Maklumat Tuntutan Kos Gantirugi</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.senaraiHakmilikPermohonan}"
                                       pagesize="10" cellpadding="0" cellspacing="0" requestURI="/pengambilan/gantiRugi" id="line">
                            <display:column title="Bil" sortable="true" style="text-align:center">${line_rowNum}</display:column>
                            <display:column title="No Hakmilik" style="vertical-align:center">
                            ${line.hakmilik.kodHakmilik.kod} ${line.hakmilik.noHakmilik}
                            </display:column>
                           <%--  <c:set value="${line.hakmilik.idHakmilik}" var="idHakmilik" />
                             <c:set value="${senarai.pihak.nama}" var="senaraiNamaPihak" />
                             <c:set value="${senarai.pihak.idPihak}" var="idPihak" />--%>
                            <display:column title="Senarai Tuan Tanah" style="vertical-align:center">
                                <c:forEach items="${actionBean.getSenaraiPihak}" var="senarai">
                                        <c:out value="${senarai.nama}"/>
                                </c:forEach>
                            </display:column>
                             <display:column title="Jenis Kerosakan" style="text-align:left">
                                <c:forEach items="${line.permohonan.senaraiTuntutanKos}" var="senarai">
                                <c:out value="${senarai.item}"/><br />
                                </c:forEach>
                            </display:column>
                             <display:column title="Amaun Yang Diminta (RM)" style="text-align:left">
                                <c:forEach items="${line.permohonan.senaraiTuntutanKos}" var="senarai">
                                    <c:out value="${senarai.amaunTuntutan}"/><br />
                                </c:forEach>
                            </display:column>
                            <%--<display:column property="item" title="Jenis Kerosakan" style="text-align:left">
                                <s:text name="senaraiTuntutanKos[${line_rowNum - 2}].item" size="40"/>
                            </display:column>
                            <display:column title="Amaun Yang Diminta (RM)" property="amaunTuntutan" style="text-align:right">
                                <div align="left"><s:text name="senaraiTuntutanKos[${line_rowNum - 1}].amaunTuntutan"
                                 size="20" class="number" onblur="javascript:updateTotal(this,${line_rowNum - 1});" id="amaun${line_rowNum - 1}"/>
                                </div>
                            </display:column>--%>
                                <%--

                            <c:if test="${detail}">
                            <display:column title="Amaun Yang Diluluskan (RM)" property="amaunSebenar" style="text-align:right">
                                <div align="center"><s:text name="senaraiTuntutanKos[${line_rowNum - 1}].amaunSebenar"
                                 size="20" class="number" onblur="javascript:updateTotal1(this,${line_rowNum - 1});" id="amaunt${line_rowNum - 1}"/></div>
                                </display:column>
                             </c:if>--%>

                           <%-- <display:column title="Kemaskini">
                                <div align="center">
                                    <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/pub/images/edit.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="popup1('${line.idKos}')"/>
                                </div>
                            </display:column>
                            --%><display:column title="Hapus">
                                <div align="center">
                                    <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                         id='rem_${line_rowNum}' onmouseover="this.style.cursor='pointer';" onclick="removeSingle('${actionBean.senaraiTuntutanKos[line_rowNum-1].idKos}');" />
                                </div>
                            </display:column>

                            <display:footer>
                                    <tr>
                                        <td colspan="4"><div align="right"><b>Jumlah Perlu Dibayar(RM):</b></div></td>
                                        <td><div align="left"><input name="jumCaraBayar" value="0.00" id="jumCaraBayar" size="20"
                                                                       class="number" readonly="true"/></div></td>

                                        <%--<td><div align="center"><input name="jumCaraBayar1" value="0.00" id="jumCaraBayar1" size="20"
                                                                       class="number" readonly="true"/></div></td>
--%>
                                </tr>
                            </display:footer>
                        </display:table>

                      <br />
                        <%--<s:hidden name="idPermohonanPihak" value="${actionBean.permohonanPihak.idPermohonanPihak}"/>--%>
                        <s:hidden name="idPihak" value="${actionBean.pihak.idPihak}"/>
                        <s:button name="new" value="Tambah" id="new" class="btn" onclick="addNew();" />
                        <s:submit name="kembali" value="Kembali" class="btn"/>
                    </div>
                </fieldset>
            </div>
        </s:form>