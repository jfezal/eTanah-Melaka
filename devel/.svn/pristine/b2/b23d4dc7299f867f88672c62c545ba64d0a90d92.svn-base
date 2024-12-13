<%--
    Document   : maklumat_perintah
    Created on : May 10, 2012, 10:36:47 AM
    Author     : muhammad.amin
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    #tdTopLabel {
        color:#003194;
        display:block;
        font-family:Tahoma;
        font-size:14px;
        font-weight:bold;
        width:150em;
        vertical-align:top;
        padding-left:8em;
    }

    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        text-align:left;
        vertical-align:top;
        padding-left:9em;
    }

    #tdLabelLeft {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        text-align:right;
        vertical-align:top;
        width:2em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
    }

</style>

<script type="text/javascript">

    $(document).ready( function(){
        $('.alphanumeric').alphanumeric();

    <%--var checkKPSH = '${actionBean.checkKPSH}';
    var checkKKP = '${actionBean.checkKKP}';
    var checkPPSH = '${actionBean.checkPPSH}';
    var checkPKP = '${actionBean.checkPKP}';
    var checkPPA = '${actionBean.checkPPA}';
        
    if(checkKPSH == "Y"){
        $('#tableKPSH').show();
        $('#buttonKPSH').show();

        }
        else{
            $('#tableKPSH').hide();
            $('#buttonKPSH').hide();
        }
        if(checkKKP == "Y"){
            $('#tableKKP').show();
            $('#buttonKKP').show();

        }
        else{
            $('#tableKKP').hide();
            $('#buttonKKP').hide();
        }
        if(checkPPSH == "Y"){
            $('#tablePPSH').show();
            $('#buttonPPSH').show();

        }
        else{
            $('#tablePPSH').hide();
            $('#buttonPPSH').hide();
        }
        if(checkPKP == "Y"){
            $('#serahanPKP').show();
            $('#tarikhPKP').show();
            $('#syerPKP').show();
        }
        else{
            $('#serahanPKP').hide();
            $('#tarikhPKP').hide();
            $('#syerPKP').hide();
        }
        if(checkPPA == "Y"){
            $('#serahanPPA').show();
            $('#tarikhPPA').show();
        }
        else{
            $('#serahanPPA').hide();
            $('#tarikhPPA').hide();
        }--%>
            });

    <%--            function changePPA(){

                if($('input[name=checkPPA]').is(':checked')){
                    $('#serahanPPA').show();
                    $('#tarikhPPA').show();
                }
                else{
                    $('#serahanPPA').hide();
                    $('#tarikhPPA').hide();
                }
            }

            function changePKP(){

                if($('input[name=checkPKP]').is(':checked')){
                    $('#serahanPKP').show();
                    $('#tarikhPKP').show();
                    $('#syerPKP').show();
                }
                else{
                    $('#serahanPKP').hide();
                    $('#tarikhPKP').hide();
                    $('#syerPKP').hide();
                }
            }--%>

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

                function addPerintah(type){
                    window.open('${pageContext.request.contextPath}/consent/maklumat_perintah?perintahPopup&type='+type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
                }

                function addPerintahCari(type){
                    window.open('${pageContext.request.contextPath}/consent/maklumat_perintah?showCarianForm&type='+type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
                }
                function remove(cn, id){
                    var len = $('.' + cn).length;
                    var param = '';
                    doBlockUI();

                    for(var i=1; i<=len; i++){
                        var ckd = $('#' + id +'_'+i).is(":checked");
                        if(ckd){
                            param = param + '&ids=' + $('#' + id +'_'+i).val();
                        }
                    }

                    if(param == ''){
                        alert('Sila pilih maklumat terlebih dahulu.');
                        doUnBlockUI();
                        return;
                    }
           
                    else{
                        var url = '${pageContext.request.contextPath}/consent/maklumat_perintah?delete'+param;

                        $.ajax({
                            type:"GET",
                            url : url,
                            dataType : 'html',
                            error : function (xhr, ajaxOptions, thrownError){
                                alert("error=" + xhr.responseText);
                                doUnBlockUI();
                            },
                            success : function(data){
                                $('#page_div').html(data);
                                //doPopupMsg("Kemaskini berjaya!");
                                doUnBlockUI();
                            }
                        });
                    }
                }
    
                function editPerintah(i,type){
                    var id = $('.'+type+i).val();
                    window.open("${pageContext.request.contextPath}/consent/maklumat_perintah?editPerintah&id="+id+"&type="+type, "eTanah",
                    "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
                }

    <%--       function changeKPSH(){

                if($('input[name=checkKPSH]').is(':checked')){
                    $('#tableKPSH').show();
                    $('#buttonKPSH').show();
                }
                else{
                    $('#tableKPSH').hide();
                    $('#buttonKPSH').hide();
                }
            }

            function changeKKP(){

                if($('input[name=checkKKP]').is(':checked')){
                    $('#tableKKP').show();
                    $('#buttonKKP').show();
                }
                else{
                    $('#tableKKP').hide();
                    $('#buttonKKP').hide();
                }
            }

            function changePPSH(){

                if($('input[name=checkPPSH]').is(':checked')){
                    $('#tablePPSH').show();
                    $('#buttonPPSH').show();
                }
                else{
                    $('#tablePPSH').hide();
                    $('#buttonPPSH').hide();
                }
            }

            function changePPA(){

                if($('input[name=checkPPA]').is(':checked')){
                    $('#serahanPPA').show();
                    $('#tarikhPPA').show();
                }
                else{
                    $('#serahanPPA').hide();
                    $('#tarikhPPA').hide();
                }
            }

            function changePKP(){

                if($('input[name=checkPKP]').is(':checked')){
                    $('#serahanPKP').show();
                    $('#tarikhPKP').show();
                    $('#syerPKP').show();
                }
                else{
                    $('#serahanPKP').hide();
                    $('#tarikhPKP').hide();
                    $('#syerPKP').hide();
                }
            }--%>

</script>
<s:form beanclass="etanah.view.stripes.consent.MaklumatPerintahActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" id="display">
        <fieldset class="aras1">
            <legend>Maklumat Perintah Tambahan</legend>
            <table border="0">
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr><td id="tdTopLabel" colspan="2"><font color="1">Kemasukan : </font></td></tr>
                <tr><td id="tdLabel" colspan="2"> <c:if test="${edit}"> <s:checkbox name="checkKPSH" value="Y" id="checkKPSH"/></c:if>
                        <c:if test="${!edit}"> <s:checkbox name="checkKPSH" value="Y" id="checkKPSH" disabled="true"/></c:if>&nbsp;Kemasukan Penghuni Seumur Hidup&nbsp;</td>
                </tr>
<%--               <tr>
                    <td colspan="2">
                        <div align="center" id="tableKPSH">
                            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listKPSH}" cellpadding="0" cellspacing="0" id="lineKPSH"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">

                                <c:if test="${edit}"><display:column title="Pilih" style="width:40;">
                                        <s:checkbox name="checkbox" id="rm_kpsh_${lineKPSH_rowNum}" value="${lineKPSH.ID}"/>
                                    </display:column></c:if>
                                <display:column title="Bil" sortable="true">${lineKPSH_rowNum}
                                    <s:hidden name="KPSH" class="KPSH${lineKPSH_rowNum -1}" value="${lineKPSH.ID}"/>
                                </display:column>
                                <display:column property="NAMA" title="Nama" class="namaKPSH"/>
                                <display:column property="PENGENALAN" title="Nombor Pengenalan"/>
                                <display:column property="SYER" title="Syer"/>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPerintah('${lineKPSH_rowNum-1}', 'KPSH');return false;" onmouseover="this.style.cursor='pointer';">

                                        </p>
                                    </display:column>
                                </c:if>
                            </display:table>
                        </div>
                    </td>
                </tr>
                <c:if test="${edit}">
                    <tr><td colspan="2" id="buttonKPSH">
                            <p align="center">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addPerintah('KPSH');"/>&nbsp;
                                <c:if test="${fn:length(actionBean.listKPSH) > 0}">
                                    <s:button name="delete" onclick="remove('namaKPSH', 'rm_kpsh');" value="Hapus" class="btn"/>&nbsp;
                                </c:if>
                            </p>
                        <td>
                    </tr>
                </c:if>--%>
                <tr><td id="tdLabel" colspan="2">  <c:if test="${edit}"> <s:checkbox name="checkKKP" value="Y" id="checkKKP"/> </c:if>
                        <c:if test="${!edit}"> <s:checkbox name="checkKKP" value="Y" id="checkKKP" disabled="true"/> </c:if>&nbsp;Kemasukan Kaveat Pendaftar&nbsp;</td>
                    <td id="tdDisplay">
                    </td>
                </tr>
                <%--             <tr>
                                 <td colspan="2">
                                     <div align="center" id="tableKKP">
                                         <display:table style="width:60%;" class="tablecloth" name="${actionBean.listKKP}" cellpadding="0" cellspacing="0" id="lineKKP"
                                                        requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                                             <display:column title="Pilih" style="width:40;">
                                                 <s:checkbox name="checkbox" id="rm_kkp_${lineKKP_rowNum}" value="${lineKKP.ID}"/>
                                             </display:column>
                                             <display:column title="Bil" sortable="true">${lineKKP_rowNum}
                                                 <s:hidden name="KKP" class="KKP${lineKKP_rowNum -1}" value="${lineKKP.ID}"/>
                                             </display:column>
                                             <display:column property="NAMA" title="Nama" class="namaKKP"/>
                                             <display:column property="PENGENALAN" title="Nombor Pengenalan"/>
                                             <c:if test="${edit}">
                                                 <display:column title="Kemaskini">
                                                     <p align="center">
                                                         <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                              onclick="editPerintah('${lineKKP_rowNum-1}', 'KKP');return false;" onmouseover="this.style.cursor='pointer';">

                                            </p>
                                        </display:column>
                                    </c:if>
                                </display:table>
                            </div></td></tr>
                    <tr><td colspan="2">
                            <p align="center" id="buttonKKP">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addPerintah('KKP');"/>&nbsp;
                                <c:if test="${fn:length(actionBean.listKKP) > 0}">
                                    <s:button name="delete" onclick="remove('namaKKP', 'rm_kkp');" value="Hapus" class="btn"/>&nbsp;
                                </c:if>
                            </p>
                        <td>
                    </tr>--%>

                <tr><td id="tdLabel" colspan="2"> <c:if test="${edit}"> <s:checkbox name="checkKPA" value="Y"/></c:if>
                        <c:if test="${!edit}"> <s:checkbox name="checkKPA" value="Y" disabled="true"/></c:if>&nbsp;Kemasukan Pemegang Amanah&nbsp;</td>

                </tr> <tr><td>&nbsp;</td></tr>
                <tr><td id="tdTopLabel" colspan="2"><font color="1">Pembatalan : </font></td></tr>
                <tr><td id="tdLabel" colspan="2"> <c:if test="${edit}">  <s:checkbox name="checkPPSH" value="Y" id="checkPPSH"/></c:if>
                        <c:if test="${!edit}">  <s:checkbox name="checkPPSH" value="Y" id="checkPPSH" disabled="true"/></c:if>&nbsp;Pembatalan Penghuni Seumur Hidup&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center" id="tablePPSH">
                            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPPSH}" cellpadding="0" cellspacing="0" id="linePPSH"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                                <c:if test="${edit}">
                                    <display:column title="Pilih" style="width:40;">
                                        <s:checkbox name="checkbox" id="rm_ppsh_${linePPSH_rowNum}" value="${linePPSH.ID}"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil" sortable="true">${linePPSH_rowNum}
                                    <s:hidden name="PPSH" class="PPSH${linePPSH_rowNum -1}" value="${linePPSH.ID}"/>
                                </display:column>
                                <display:column property="NAMA" title="Nama Penghuni" class="namaPPSH"/>
                                <display:column property="SERAHAN" title="Nombor Perserahan"/>
                                <display:column property="TARIKH" title="Tarikh Serahan"/>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPerintah('${linePPSH_rowNum-1}', 'PPSH');return false;" onmouseover="this.style.cursor='pointer';">

                                        </p>
                                    </display:column>
                                </c:if>
                            </display:table>
                        </div></td></tr>
                        <c:if test="${edit}">
                    <tr><td colspan="2">
                            <p align="center" id="buttonPPSH">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addPerintahCari('PPSH');"/>&nbsp;
                                <c:if test="${fn:length(actionBean.listPPSH) > 0}">
                                    <s:button name="delete" onclick="remove('namaPPSH', 'rm_ppsh');" value="Hapus" class="btn"/>&nbsp;
                                </c:if>
                            </p>
                        <td>
                    </tr>
                </c:if>
                <tr><td id="tdLabel" colspan="2"> <c:if test="${edit}"><s:checkbox name="checkPKP" value="Y" id="checkPKP"/></c:if>
                        <c:if test="${!edit}"><s:checkbox name="checkPKP" value="Y" id="checkPKP" disabled="true"/></c:if>
                        &nbsp;Pembatalan Kaveat Pendaftar&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center" id="tablePKP">
                            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPKP}" cellpadding="0" cellspacing="0" id="linePKP"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                                <c:if test="${edit}">
                                    <display:column title="Pilih" style="width:40;">
                                        <s:checkbox name="checkbox" id="rm_pkp_${linePKP_rowNum}" value="${linePKP.ID}"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil" sortable="true">${linePKP_rowNum}
                                    <s:hidden name="PKP" class="PKP${linePKP_rowNum -1}" value="${linePKP.ID}"/>
                                </display:column>
                                <display:column property="NAMA" title="Nama Pemegang Amanah (Dibatalkan)" class="namaPKP"/>
                                <display:column property="SERAHAN" title="Nombor Perserahan"/>
                                <display:column property="TARIKH" title="Tarikh Serahan"/>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPerintah('${linePKP_rowNum-1}', 'PKP');return false;" onmouseover="this.style.cursor='pointer';">

                                        </p>
                                    </display:column>
                                </c:if>
                            </display:table>
                        </div></td></tr>

                <c:if test="${edit}">
                    <tr><td colspan="2">
                            <p align="center" id="buttonPKP">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addPerintahCari('PKP');"/>&nbsp;
                                <c:if test="${fn:length(actionBean.listPKP) > 0}">
                                    <s:button name="delete" onclick="remove('namaPKP', 'rm_pkp');" value="Hapus" class="btn"/>&nbsp;
                                </c:if>
                            </p>
                        <td>
                    </tr>
                </c:if>
                <%--                    <tr id="namaPKP"><td id="tdLabelLeft">Nama Penghuni (Dibatalkan)&nbsp;:  </td>
                                        <td id="tdDisplay"> <s:text name="namaPKP"  size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                                    </tr>
                                    <tr id="serahanPKP"><td id="tdLabelLeft">Nombor Perserahan&nbsp;:  </td>
                                        <td id="tdDisplay"> <s:text name="noSerahanPKP"  size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                                    </tr>
                                    <tr id="tarikhPKP"><td id="tdLabelLeft">Tarikh Perserahan&nbsp;: </td>
                                        <td id="tdDisplay"> <s:text name="tarikhPKP"   id="datepicker" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/></td>
                                    </tr>
                                    <tr><td>&nbsp; </td></tr>--%>
                <tr><td id="tdLabel" colspan="2">  <c:if test="${edit}"><s:checkbox name="checkPPA" value="Y" id="checkPPA"/></c:if>
                        <c:if test="${!edit}"><s:checkbox name="checkPPA" value="Y" id="checkPPA" disabled="true"/></c:if>
                        &nbsp;Pembatalan Pemegang Amanah&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center" id="tablePPA">
                            <display:table style="width:60%;" class="tablecloth" name="${actionBean.listPPA}" cellpadding="0" cellspacing="0" id="linePPA"
                                           requestURI="${pageContext.request.contextPath}/consent/maklumat_perintah">
                                <c:if test="${edit}">
                                    <display:column title="Pilih" style="width:40;">
                                        <s:checkbox name="checkbox" id="rm_ppa_${linePPA_rowNum}" value="${linePPA.ID}"/>
                                    </display:column>
                                </c:if>
                                <display:column title="Bil" sortable="true">${linePPA_rowNum}
                                    <s:hidden name="PPA" class="PPA${linePPA_rowNum -1}" value="${linePPA.ID}"/>
                                </display:column>
                                <display:column property="NAMA" title="Nama Pemegang Amanah (Dibatalkan)" class="namaPPA"/>
                                <display:column property="SERAHAN" title="Nombor Perserahan"/>
                                <display:column property="TARIKH" title="Tarikh Serahan"/>
                                <c:if test="${edit}">
                                    <display:column title="Kemaskini">
                                        <p align="center">
                                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                                 onclick="editPerintah('${linePPA_rowNum-1}', 'PPA');return false;" onmouseover="this.style.cursor='pointer';">

                                        </p>
                                    </display:column>
                                </c:if>
                            </display:table>
                        </div></td></tr>
                        <c:if test="${edit}">
                    <tr><td colspan="2">
                            <p align="center" id="buttonPPA">
                                <s:button class="btn" value="Tambah Baru" name="new" id="new" onclick="addPerintahCari('PPA');"/>&nbsp;
                                <c:if test="${fn:length(actionBean.listPPA) > 0}">
                                    <s:button name="delete" onclick="remove('namaPPA', 'rm_ppa');" value="Hapus" class="btn"/>&nbsp;
                                </c:if>
                            </p>
                        <td>
                    </tr>
                </c:if>
                <%--           <tr id="namaPPA"><td id="tdLabelLeft">Nama Pemegang Amanah (Dibatalkan)&nbsp;:  </td>
                               <td id="tdDisplay"> <s:text name="namaPPA"  size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                           </tr>
                           <tr id="serahanPPA"><td id="tdLabelLeft">Nombor Perserahan&nbsp;:  </td>
                               <td id="tdDisplay"> <s:text name="noSerahanPPA"  size="40" onkeyup="this.value=this.value.toUpperCase();"/></td>
                           </tr>
                           <tr id="tarikhPPA"><td id="tdLabelLeft">Tarikh Perserahan&nbsp;: </td>
                               <td id="tdDisplay"> <s:text name="tarikhPPA"   id="datepicker2" size="40" class="datepicker" onkeyup="this.value=this.value.toUpperCase();"/></td>
                           </tr>--%>
            </table>
            <c:if test="${edit}">
                <br>
                <p align="center">
                    <%--<label>&nbsp;</label>--%>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>

        </fieldset>
    </div>
</s:form>

