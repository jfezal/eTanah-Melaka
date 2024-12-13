<%-- 
    Document   : penghantar_notis
    Created on : Oct 9, 2012, 10:52:42 AM
    Author     : muhammad.amin
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
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.blockUI.js"></script>
<script type="text/javascript">


    $(document).ready(function(){
        $('form').submit(function() {
            doBlockUI();
        });
    });


    function simpan(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
        },'html');
    }

    function validateNumber(elmnt,content) {
        //if it is character, then remove it..
        if (isNaN(content)) {
            elmnt.value = RemoveNonNumeric(content);
            return;
        }
    }

    function edit(i){
        var d = $('.x'+i).val();
        window.open("${pageContext.request.contextPath}/consent/penghantar_notis?showEdit&idPenghantarNotis="+d, "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=2000,height=900,scrollbars=yes,fullscreen=yes");
    }

    function hapus(i){
        doBlockUI();
        var d = $('.x'+i).val();
        if(confirm('Adakah pasti untuk hapus?')){
            var url = '${pageContext.request.contextPath}/consent/penghantar_notis?delete&idPenghantarNotis='+d;
            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
        $.unblockUI();
    }

    function doBlockUI () {
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }


    function RemoveNonNumeric( strString )
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

    function test(f) {
        $(f).clearForm();
    }

    function clearForm(f) {

        $("#nama").val('');
        $("#jenisKp").val('');
        $("#noKp").val('');
    }

    function dodacheck(val) {
        //var mikExp = /[1\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\`\~\1\|]/;
        var v = $('#jenisKp').val();

        if(v == 'B') {
            var strPass = val;
            var strLength = strPass.length;
            //$('#kp').attr("maxlength","12");
            if(strLength > 12) {
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
            var lchar = val.charAt((strLength) - 1);
            if(isNaN(lchar)) {
                //return false;
                var tst = val.substring(0, (strLength) - 1);
                $('#noKp').val(tst);
            }
        }
    }

    function doBlockUI (){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
    }


</script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<img id="displayBox" src="${pageContext.request.contextPath}/pub/images/logo.gif" width="50" height="50" style="display: none" alt=""/>
<s:form beanclass="etanah.view.stripes.consent.PenghantarNotisActionBean">
    <div class="subtitle displaytag"  id="page_div">
        <fieldset class="aras1" id="">
            <s:messages />
            <s:errors />

            <legend>
                Maklumat Penghantar Notis
            </legend>
            <p>
                <label><font color="red">*</font> Nama : </label>
                <s:text id="nama" name="penghantarNotis.nama" onkeyup="this.value=this.value.toUpperCase();" style="width:260px;"/>
            </p>
            <p>
                <label><font color="red">*</font>Jenis Pengenalan : </label>
                <s:select id="jenisKp" name="penghantarNotis.kodJenisPengenalan.kod" style="width:150px;">
                    <s:option value="">Sila Pilih</s:option>
                    <s:options-collection collection="${listUtil.senaraiJenisPengenalanIndividu}" label="nama" value="kod"/>
                </s:select>
            </p>
            <p>
                <label> <font color="red">*</font>Nombor Pengenalan : </label>
                <s:text id="noKp" name="penghantarNotis.noPengenalan" onkeyup="dodacheck(this.value);this.value=this.value.toUpperCase()" style="width:150px;" maxlength="20"/>
                <font color="red" size="1">(cth : 550201045678)</font>
            </p>
            <div class="content" align="center">
                <p>
                    <s:submit name="simpan" id="save" value="Simpan" class="btn"/>
                    <s:button name="reset" value="Isi Semula" class="btn" onclick="clearForm(this.form);"/>
                </p>
            </div>
        </fieldset>
        <br/>
        <fieldset class="aras1">
            <legend>
                Senarai Penghantar Notis
            </legend>
            <div align="center">
                <display:table class="tablecloth" name="${actionBean.listPenghantar}" cellpadding="0" cellspacing="0" id="line" pagesize="20" requestURI="${pageContext.request.contextPath}/consent/penghantar_notis">
                    <display:column title="Bil" sortable="true">${line_rowNum}
                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.idPenghantarNotis}"/>
                    </display:column>
                    <display:column property="nama" title="Nama" />
                    <display:column title="Jenis Pengenalan">
                        <c:choose>
                            <c:when test="${line.kodJenisPengenalan.kod eq 'B'}">
                                No K/P Baru
                            </c:when>
                            <c:when test="${line.kodJenisPengenalan.kod eq 'L'}">
                                No K/P Lama
                            </c:when>
                            <c:when test="${line.kodJenisPengenalan.kod eq 'P'}">
                                No Pasport
                            </c:when>
                            <c:when test="${line.kodJenisPengenalan.kod eq 'T'}">
                                No Tentera
                            </c:when>
                            <c:when test="${line.kodJenisPengenalan.kod eq 'I'}">
                                No Polis
                            </c:when>
                            <c:otherwise>
                                ${line.kodJenisPengenalan.kod}
                            </c:otherwise>
                        </c:choose>
                    </display:column>
                    <display:column property="noPengenalan" title="Nombor Pengenalan" />
                    <display:column title="Kemaskini">
                        <p align="center">
                            <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                 onclick="edit('${line_rowNum -1}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                    <display:column title="Hapus">
                        <p align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif'
                                 onclick="hapus('${line_rowNum -1}');return false;" onmouseover="this.style.cursor='pointer';">
                        </p>
                    </display:column>
                </display:table>
                &nbsp;
            </div>
        </fieldset>
    </div>
</s:form>
