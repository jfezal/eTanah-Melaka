<%--
    Document   : BayaranKerosakanPtSp
    Created on : 02-Nov-2011, 15:07:32
    Author     : Murali
--%>

<%@ page language="java" contentType="text/html;" import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="etanah.model.Pengguna"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.datepicker.js"></script>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function showTunai(){
        $("#nodok").attr("disabled",false);
        $("#tarikhDok").attr("disabled",false);
        $("#kodBank").attr("disabled",false);
    }

    function hideTunai(){
        $("#nodok").attr("disabled",true);
        $("#nodok").val("");
        $("#tarikhDok").attr("disabled",false);
        $("#tarikhDok").val("");
        $("#kodBank").attr("disabled",true);
        $("#kodBank").val("");
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = removeNonNumeric(content);
            return;
        }
    }
    function removeNonNumeric( strString )
    {
        var strValidCharacters = "1234567890";
        var strReturn = "";
        var strBuffer = "";
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
    function textCounter(field, countfield, maxlimit) {
        if (field.value.length > maxlimit) // if too long...trim it!
            field.value = field.value.substring(0, maxlimit);
        // otherwise, update 'characters left' counter
        else
            countfield.value = maxlimit - field.value.length;
    }

    function validation() {
        if($("#jumTerimaPampasan").val() == ""){
            alert('Sila pilih " Jumlah pampasan yang diterima (RM) : " terlebih dahulu.');
            $("#jumTerimaPampasan").focus();
            return false;
        }
        if ($("input[name='kodCaraBayaran']:checked").val() != 'CT' &&
            $("input[name='kodCaraBayaran']:checked").val() != 'CL' &&
            $("input[name='kodCaraBayaran']:checked").val() != 'CB' &&
            $("input[name='kodCaraBayaran']:checked").val() != 'DB' &&
            $("input[name='kodCaraBayaran']:checked").val() != 'T' &&
            $("input[name='kodCaraBayaran']:checked").val() != 'XT') {
            alert('Sila pilih " Cara Pembayaran : " terlebih dahulu.');
            $("#kodCaraBayaran1").focus();
            return false;
        }else if($("input[name='kodCaraBayaran']:checked").val() != 'T'){
            $("#nodok").attr("disabled",false);
            $("#tarikhDok").attr("disabled",false);
            $("#kodBank").attr("disabled",false);

        }
        return true;

    }

    function showforTunai() {
        $('#nodok').show();
        $('#datepicker').show();
        $('#kodBank').show();
        $('#no').show();
        $('#tarikh').show();
        $('#tarikhDok').show();
        $('#bank').show();
        $("#noDok").val()="";
        $("#tarikhDok").val()="";
        $('#kodBank').val()=="";
    }
    function HideforTunai() {
        $('#nodok').hide();
        $('#datepicker').hide();
        $('#kodBank').hide();
        $('#no').hide();
        $('#tarikh').hide();
        $('#tarikhDok').hide();
        $('#bank').hide();
        $("#nodok").attr("disabled",true);
        $("#nodok").val("");
        $("#tarikhDok").attr("disabled",true);
        $("#tarikhDok").val("");
        $("#kodBank").attr("disabled",true);
        $("#kodBank").val("");
    }


    function validateAmount(idVar){

        var amounttuntut = parseInt(idVar);
        var amountsebenar = parseInt($('#amountsebenar').val());
        alert(amountsebenar);
        if(amountsebenar < amounttuntut){
            alert("Pastikan jumlah yang dibayar tidak melebihi jumlah yang dikehendaki");
            $('#jumTerimaPampasan').val("");
            $('#jumTerimaPampasan').focus();
            return true;
        }
    }

    <%--function scan(idPihak) {
        alert(idPihak)
        var idHakmilik = $('#idHakmilik').val();
        var showPP = $('#showPP').val();
        var showHP = $('#showHP').val();
        var url = '${pageContext.request.contextPath}/pengambilan/BayaranKerosakanPTSP?popupScan&idPihak='+idPihak+'&idHakmilik='+idHakmilik;
        window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=800,height=700");
    }
    --%>
        function scan(dokumenId, idPermohonan) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/dokumen/scan/' + dokumenId + '?idPermohonan=' + idPermohonan;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=850,height=600");
        }

        function muatNaikForm(folderId, idPermohonan, dokumenKod) {
            var left = (screen.width/2)-(1000/2);
            var top = (screen.height/2)-(150/2);
            var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId+ '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
        }

        function doViewReport(v) {
            var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
            window.open(url, 'etanah2', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
        }

        function validateAmount(idVar){
            var str1 = "a,d,k";
            str1.replace(/\,/g,"");
            var str2 = str1.replace(/\,/g,"");
            var amounttuntut = idVar;
            var amountsebenar = $('#amountsebenar').val();
            var aaa= amountsebenar.replace(/\,/g,"");
            var am =aaa.indexOf(".00");

            var ab = aaa.substring(0, am);

            var ad = amounttuntut.replace(/\,/g,"");
            var amm =ad.indexOf(".00");
            var ac;
            if(amm=='-1'){
                ac = amounttuntut;
            }else{
                ac = ad.substring(0, amm);
            }
            if(ac != ab){
                alert("Pastikan jumlah yang dibayar sama dengan jumlah yang dikehendaki");
                $('#jumTerimaPampasan').val("");
                $('#jumTerimaPampasan').focus();
                return true;
            }
        }

        function ajaxLink(link, update) {
    <%--alert(link);--%>
            $.get(link, function(data) {
                $(update).html(data);
                $(update).show();
            });
            return false;
        }
</script>
<div class="subtitle" id="caw">
    <s:form name="form1" id="form1" beanclass="etanah.view.stripes.pengambilan.BayaranKerosakanPTSPActionBean">
        <s:messages/>
        <s:errors/>
        <div  id="hakmilik_details">
            <s:hidden name="showHP" value="${actionBean.showHP}" id="showHP"/>
            <s:hidden name="index" id="index" />
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>
                <p>Arahan :<br><font size="1" color="red"> Sila klik ke Tab Dokumen untuk muat naik dokumen</font></p>
                <br/>
                <div align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/pengambilan/BayaranKerosakanPTSP" id="line">
                        <display:column title="No" sortable="true">${line_rowNum}</display:column>
                        <display:column property="hakmilik.idHakmilik" title="No Hakmilik"/>
                        <display:column title="No Lot/No PT" >${line.hakmilik.lot.nama}${line.hakmilik.noLot}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="Daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <display:column title="Tuan Tanah" >
                            <c:set value="1" var="count"/>
                            <c:forEach items="${line.hakmilik.senaraiPihakBerkepentingan}" var="senarai">
                                <c:if test="${senarai.aktif eq 'Y' }">
                                    <s:link beanclass="etanah.view.stripes.pengambilan.BayaranKerosakanPTSPActionBean"
                                            event="pihakDetails" onclick="return ajaxLink(this, '#hakmilik_details');" >
                                        <s:param name="idPihak" value="${senarai.pihak.idPihak}"/>
                                        <s:param name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>
                                        <c:out value="${count}"/>)&nbsp;
                                        ${senarai.pihak.nama} <br/>
                                    </s:link>
                                    <c:set value="${count + 1}" var="count"/>
                                    <br/>
                                </c:if>
                            </c:forEach>
                        </display:column>
                    </display:table>
                </div>
            </fieldset>
            <c:if test="${showDetails}">
                <s:errors/>
                <c:if test="${actionBean.show eq true }">
                    <div  class="subtitle">
                        <fieldset class="aras1"><br />
                            <legend>Maklumat Pembayaran</legend><br />
                            <div  align="center">
                                <table width="100%">
                                    <tr>
                                        <td width="30%"><label >Jumlah Pampasan (RM) :</label></td>
                                        <td><s:text name="amountsebenar" id="amountsebenar" formatPattern="#,##0.00" readonly="true"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="30%"><label >Amaun Diterima  (RM) :</label></td>
                                        <td><s:text name="jumTerimaPampasan" formatPattern="#,##0.00" id="jumTerimaPampasan" size="50" onblur="validateAmount(this.value);"/><br/></td>
                                    </tr>
                                    <tr>
                                        <td><label >Cara Pembayaran : </label></td>
                                        <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran1" value="CT" onclick="showTunai();"/> Cek Tempatan <br /></td>
                                    </tr>
                                    <tr>
                                        <td><label >&nbsp;&nbsp;</label></td>
                                        <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran2" value="CL" onclick="showTunai();"/> Cek Luar <br /></td>
                                    </tr>
                                    <tr>
                                        <td><label >&nbsp;&nbsp;</label></td>
                                        <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran3" value="CB" onclick="showTunai();"/> Cek Bank Negara <br /></td>
                                    </tr>
                                    <tr>
                                        <td><label >&nbsp;&nbsp;</label></td>
                                        <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran4" value="DB" onclick="showTunai();"/> Bank Draf <br /></td>
                                    </tr>
                                    <tr>
                                        <td><label >&nbsp;&nbsp;</label></td>
                                        <td><s:radio name="kodCaraBayaran" id="kodCaraBayaran5" value="T" onclick="hideTunai();"/> Tunai <br /></td>
                                    </tr>
                                    <tr>
                                        <td><label id="no" >No. :</label></td>
                                        <td><s:text name="noDok" disabled="${actionBean.tunai}" size="50" id="nodok" onkeyup="validateNumber(this,this.value);"/></td>
                                    </tr>
                                    <tr>
                                        <td><label id="tarikh" >Tarikh :</label></td>
                                        <td><s:text name="tarikhDok" disabled="${actionBean.xtunai}" id="tarikhDok" class="datepicker" formatPattern="dd/MM/yyyy"/></td>
                                    </tr>
                                    <tr>
                                        <td><label id="bank" >Bank :</label></td>
                                        <td><s:select name="kodBank" disabled="${actionBean.tunai}" style="width:300px;" id="kodBank" >
                                                <s:option value="">-- Sila Pilih --</s:option>
                                                <s:options-collection collection="${listUtil.senaraiKodBank}" value="kod" label="nama"/>
                                            </s:select>
                                        </td>
                                    </tr>
                                    <%--<div>
                                        <tr align="left">
                                            <td><label id="Tindakan" >Tindakan :</label></td>
                                            <td>
                                                <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                                <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/upload.png" height="30" width="30" alt="Muat Naik"
                                                     onmouseover="this.style.cursor='pointer';" onclick="muatNaikForm('${actionBean.permohonan.idPermohonan}', '${actionBean.permohonan.folderDokumen.folderId}', 'DUP');return false;" title="Muat Naik Dokumen"/>
                                                /
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/scanner.png" height="30" width="30" alt="Imbas"
                                                     onmouseover="this.style.cursor='pointer';" onclick="scan('${actionBean.idPihak}','${actionBean.permohonan.folderDokumen.folderId}');return false;" title="Imbas Dokumen"/>
                                                /
                                                <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png" height="30" width="30" alt="papar"
                                                     onclick="doViewReport('${actionBean.idPihak}');" onmouseover="this.style.cursor='pointer';" title="Papar Dokumen"/>
                                            </td>
                                        </tr>
                                    </div>--%>
                                </table>
                                <br/><br/>
                                <div align="center">
                                    <s:hidden name="idPihak" value="${actionBean.idPihak}"/>
                                    <s:hidden name="idHakmilik" value="${actionBean.idHakmilik}"/>
                                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="if(validation())doSubmit(this.form, this.name, 'page_div')"/>
                                </div>
                                <br/>
                            </div>
                        </fieldset>
                    </div>
                </c:if>
            </c:if>
        </div>
    </s:form>
</div>