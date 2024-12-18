<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/scripts/jquery-1.3.2.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>
<table width="100%" bgcolor="green">
    <tr>
        <td width="50%" height="20" ><div style="float:left;" class="formtitle">PELARASAN KOD CUKAI</div></td>
        <td width="50%"height="20" ><div style="float:right;" class="formtitle"></div></td>
    </tr>
</table>
<s:form beanclass="etanah.view.stripes.PelarasanCukaiActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Akaun</legend>
            <p>
                <label>Nombor Akaun :</label>
                126389411
            </p>
            <p >
                <label>Nama Pembayar :</label>
                Ahmad
            </p>
            <p>
                <label>Bandar/Mukim/Pekan :</label>
                ara damansara
            </p>
            <p>
                <label>Hakmilik :</label>
                GM13
            </p>
        </fieldset>
    </div>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Pelarasan</legend>
            <p>
                <label>Alasan :</label>
                <s:select name="">
                    <s:option value=" "> </s:option>
                    <s:option value=" "> </s:option>
                </s:select>
            </p>
            <p>
                <label>Jenis Hasil :</label>
                <s:select name="">
                    <s:option value=" "> </s:option>
                    <s:option value=" "> </s:option>
                </s:select>
            </p>
            <p >
                <label>Keterangan :</label>
                <s:textarea name="" cols="50" rows="5" class="input1"/>
            </p>
            <p >
                <label><em>*</em>Amaun (RM) :</label>
                <s:text name=""/>
            </p>
            <p>
                <label><em>*</em>Jenis :</label>
                <s:radio name="a1" value="Nota Debit" class="input1"/>Nota Debit &nbsp;
                <s:radio name="a1" value="Nota Kredit" class="input1"/>Nota Kredit
            </p>
        </fieldset>
    </div>

    <table width="100%" bgcolor="green">
        <tr align="right">
            <td colspan="2">
                <s:button name="" value="Simpan" class="btn" id="close"/>
                <s:reset name="terus" value="Isi Semula" class="btn"/>
                <s:submit name="keluar" value="Keluar" class="btn"/>
            </td>
        </tr>
    </table>

</s:form>