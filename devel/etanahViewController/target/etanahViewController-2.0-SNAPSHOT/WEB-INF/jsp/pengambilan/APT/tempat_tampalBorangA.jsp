<%--
    Document   : maklumat_hakmilik_pengambilan
    Created on : 12-Jan-2010, 18:31:55
    Author     : nordiyana
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript">
    $(document).ready(function() {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $("#trhTampal").datepicker({dateFormat: 'dd/mm/yy'});
    });



    function save(event, f) {
        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);

                }, 'html');

    }

//    function addRow() {
//
//        var rowNo = $('table#line1 tr').length;
//        $('table#line1 > tbody').append("<tr id='x" + rowNo + "' class='x'>\n\
//                <td class='rowNo'>" + rowNo + "</td>\n\
//                <td><textarea name=tmptTampal name=tmptTampal rows=3 cols=40/></td>\n\\n\
//<td><textarea name=ulasanArray rows=3 cols=40/></td>\n\
//<td><input type=text id=trhTampal name=trhTampal class=datepicker size=20/></td>\n\
//                </tr>");
//    }

    function select(id) {
        doBlockUI();
        frm = document.form1;
        var url = '${pageContext.request.contextPath}/daftar/pihak_kepentingan?selectPihak&idPihak=' + id;
        frm.action = url;
        frm.submit();
    }

    function removeRecord(idBorang, idMohonHakmilik) {
//       alert (idBorang);
//       alert (idMohonHakmilik);

        if (confirm('Adakah anda pasti untuk hapus?')) {
           alert (idBorang);
            var url = '${pageContext.request.contextPath}/pengambilan/common/borangA?hapusRecord&idBorang=' + idBorang + '&idMohonHakmilik=' + idMohonHakmilik;
            $.get(url,
                    function(data) {
                    }, 'html');
        }
    }
//    function removeRecord(idBorang, idMohonHakmilik, f, frm)
//    {
//        alert(idBorang);
//        alert(idMohonHakmilik);
//
//        var url = '${pageContext.request.contextPath}pengambilan/common/borangA?hapusRecord&idBorang=' + idBorang + '&idAksesori=' + idMohonHakmilik;
//        frm.action = url;
//        frm.submit();

//    }
</script>

<s:form beanclass="etanah.view.stripes.pengambilan.share.common.NotisBorangAActionBean">
    <s:messages/>
    <div class="subtitle displaytag">
        <s:hidden name="idMohonHakmilik" id="idMohonHakmilik" value = "${actionBean.idMohonHakmilik}"/>

        <br><br><br>
        <fieldset class="aras1" id="locate">
            <legend>
                Perincian Borang A 

            </legend>

            <p align="center">

                <display:table class="tablecloth" name="${actionBean.tampalBorangHakmilikList2}" pagesize="30" cellpadding="0" cellspacing="0"
                               requestURI="" id="line">
                    <display:column title="No" sortable="true">${line_rowNum}</display:column>

                    <display:column title="Tempat Tampal" >${line.tempatTampal}</display:column>
                    <display:column title="Penampal" >${line.penghantarNotis}</display:column>
                    <display:column title="Catatan" >${line.catatan}</display:column>
                    <display:column title="Tarikh Tampal" >${line.tarikhTampal}</display:column>
                    <display:column title="Hapus" >
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onmouseover="this.style.cursor = 'pointer';" onclick="removeRecord('${line.id}', '${actionBean.idMohonHakmilik}');"/>
                    </div>
                </display:column>
            </display:table>
            <br>
            <div class="content" align="center">

                <display:table name="${actionBean.hakmilikPermohonan}" id="line1" class="tablecloth">

                    <display:column title="Tempat Tampal "> <s:textarea name="tmptTampal" value="" rows="3" cols="40"/></display:column>

                    <display:column title="Penampal"> <s:textarea name ="Penampal" value="" rows="3" cols="40"/></display:column>
                    <display:column title="Catatan"> <s:textarea name ="catatan" value="" rows="3" cols="40"/></display:column>
                    <display:column title="tarikh Tampal"> <s:text name="trhTampal" class="datepicker" formatPattern="dd/MM/yyyy"/>&nbsp; </display:column>
                </display:table>
                <%--<s:button class="btn" value="Tambah" name="pilih" id="pilih" onclick="addRow();"/>&nbsp;--%>
                <s:submit name="simpanBorang" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>&nbsp;

            </div>
            <br>                
        </fieldset>

    </div>

</s:form>
