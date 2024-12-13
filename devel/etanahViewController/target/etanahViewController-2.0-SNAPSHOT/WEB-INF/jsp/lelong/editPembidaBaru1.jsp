<%-- 
    Document   : editPembidaBaru1
    Created on : May 28, 2012, 5:43:45 PM
    Author     : mazurahayati.yusop
--%>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script>
    function test1(frm){
        var idLelong =frm.idLelong.value;
        var idPembida =frm.idPembida.value;
        var kodsts =frm.kodsts.value;
        url ="${pageContext.request.contextPath}/lelong/senaraipembida?simpanEditPembida&idLelong="+idLelong+"&idPembida="+idPembida+"&kodsts="+kodsts;
        window.location = url;
        self.opener.refreshingPagingFolder(idLelong);
        self.close();
    }
</script>

<s:form name="pemohon" id="pemohon"  beanclass="etanah.view.stripes.lelong.UtilitiSenaraiPermohonanLelonganPembidaActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <s:hidden name="pihak.idPihak"/>
    <s:hidden id="idPembida" name="pembida.idPembida" value="${pembida.idPembida}"/>
    <s:hidden name="idPermohonan" value="${permohonan.idPermohonan}"/>
    <s:hidden id="idLelong" name="idLelong" value="${actioBean.idLelong}"/>&nbsp;

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Pembida ${actionBean.idLelong}
            </legend>
            <div class="content">

                <p>
                    <label> Nama :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.nama} </font>
                </p>
                <p>
                    <label> No. Pengenalan :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.noPengenalan} </font>
                </p>
                <p>
                    <label>No. Bank Draf: </label>
                    <font style="text-transform:uppercase;">${actionBean.pembida.noRujukan} </font>
                </p>

                <p>
                    <label> No. Telefon :</label>
                    <font style="text-transform:uppercase;">${actionBean.pihak.noTelefon1} </font>
                </p>

                <p>
                    <label> Status :</label>
                    <s:select id="" name="kodsts" style="width:200px;">
                        <s:option value="">Sila Pilih</s:option>
                        <s:options-collection collection="${listUtil.senaraiKodStsPembida}" label="nama" value="kod"/>
                    </s:select>
                </p>

            </div>
        </fieldset>
    </div>
    <div class="content" align="center">
        <p>
            <s:button name="simpanEditPembida" id="simpanlee" value="Simpan" class="btn" onclick="test1(this.form)"/>
            <s:reset name="" value="Isi Semula" class="btn"/>&nbsp;
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        </p>
    </div><br>

</s:form>


