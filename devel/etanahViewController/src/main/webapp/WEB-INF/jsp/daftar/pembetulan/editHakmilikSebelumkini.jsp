<%--
    Document   : popup_editAsas
    Created on : Feb 23, 2010, 2:05:49 PM
    Author     : mohd.fairul
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/popup.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery-impromptu.js"></script>
<style type="text/css">
    td.s{
        font-weight:normal;
        color:black;
        text-align: left;
    }
</style>

<script language="javascript">
    $(document).ready(function() {
        $('.alphanumeric').alphanumeric();
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});

        $('input').focus(function() {
            $(this).addClass("focus");
        });

        $('input').blur(function() {
            $(this).removeClass("focus");
        });

        $('select').focus(function() {
            $(this).addClass("focus");
        });

        $('select').blur(function() {
            $(this).removeClass("focus");
        });
    });


    function save(event, f, idH)
    {



        var q = $(f).formSerialize();
        var url = f.action + '?' + event + '&idH=' + idH;

        $.post(url, q,
                function(data) {
                    $('#page_div', opener.document).html(data);
                }, 'html');

        $.prompt('Maklumat Berjaya Disimpan.', {
            buttons: {Ok: true},
            prefix: 'jqismooth',
            submit: function(v, m, f) {
                self.close();
            }
        });

    }

    function deleteHakmilikPihak(val) {

        if (confirm('Adakah anda pasti??')) {
            var url = '${pageContext.request.contextPath}/daftar/pembetulan/betul?deleteHakmilikPihak&idSblm=' + val;
            $.get(url,
                    function(data) {
                        $('#page_div').html(data);
                        self.close(); //edit yus 16032018 (function tak run bila button click butang hapus
                    }, 'html');
                    //self.close(); asal di sini
        }
    }

</script>

<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form name="form1" beanclass="etanah.view.stripes.nota.pembetulanActionBean">

    <s:messages />
    <s:errors />

    <div class="subtitle">



        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik Sebelum
            </legend>
            <p style="color:red">
                *Isi ruang pembetulan kemudian tekan butang simpan.<br/>

            </p>
            <display:table style="width:90%;" class="tablecloth" name="${actionBean.hakmilikSebelumListLama}"
                           cellpadding="0" cellspacing="0" id="line">

                <display:column title="Bil" sortable="true">${line_rowNum}</display:column>
                <display:column property="hakmilik.idHakmilik" title="nama" class="nama" />
                <display:column property="hakmilikSebelum" title="Jenis Pihak"/>

                <display:column title="Hapus">
                    <div align="center">
                        <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                             id='rem_${line_rowNum}' onclick="deleteHakmilikPihak('${line.idSebelum}')" onmouseover="this.style.cursor = 'pointer';">
                    </div>
                </display:column>

            </display:table>

            <table style="margin-left: auto; margin-right: auto;">
                <tr>
                    <td>&nbsp;</td>
                    <td><div >

                            <%--<s:button name="saveBetulSebelum" value="Simpan" class="btn" onclick="save(this.name, this.form, '${actionBean.hakmilik.idHakmilik}')"/>--%>
                            <s:button name="tutup" value="Tutup" class="btn" onclick="javascript:self.close()"/>

                        </div>
                    </td>
                </tr>
            </table>
                        <p>
                            <label>&nbsp;</label>
            <%--<s:button name="add" onclick="doOpen2('${actionBean.permohonan.idPermohonan}');" value="tambah" class="btn"/>--%>
        </p>
            </p>
            <br/>
        </fieldset>


    </div>

</s:form>