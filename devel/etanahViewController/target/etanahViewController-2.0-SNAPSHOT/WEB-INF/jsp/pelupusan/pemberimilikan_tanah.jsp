<%-- 
    Document   : pemberimilikan_tanah
    Created on : May 20, 2010, 4:21:11 PM
    Author     : nurul.izza
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jspf" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
 function addPemohon(){
        window.open("${pageContext.request.contextPath}/pelupusan/permohonanlpspt?showTambahPemohon", "eTanah",
        "status=0,toolbar=0,location=0,menubar=0,width=910,height=700,scrollbars=yes");
    }
 </script>

<s:form beanclass="etanah.view.stripes.pelupusan.PemberimilikanMlkActionBean">

    <s:messages />
    <s:errors />
    <div class="subtitle">
        <fieldset class="aras1">

            <legend>Kemasukan Maklumat Pemohon</legend>

            <div class="content" align="center">
                <display:table class="tablecloth" id="line" name="">
                    <display:column  title="Nama"/>
                    <display:column  title="Nombor Pengenalan" />
                    <display:column title="Alamat" />
                    <display:column  title="Poskod" />
                    <display:column  title="Negeri" />
                    <display:column title="Kemaskini"><a href="#" onclick="kemaskini('${line_rowNum -1}');return false;">Kemaskini</a></display:column>
                    <display:column title="Hapus">
                        <div align="center">
                            <img alt='Klik Untuk Hapus' border='0' src='${pageContext.request.contextPath}/images/not_ok.gif' class='rem'
                                 id='rem_${line_rowNum}' onclick="removePemohon('${line.idPemohon}')">
                        </div>
                    </display:column>

                </display:table>
            </div>

            <p>
                <label>&nbsp;</label>
                <s:button class="btn" value="Tambah" name="new" id="new" onclick="addPemohon();"/>&nbsp;
            </p>
            <br>

        </fieldset>
    </div>

</s:form>


