<%-- 
    Document   : penyediaan_borang_batal_endorsan.jsp
    Created on : Oct 20, 2011, 11:49:30 AM
    Author     : haqqiem
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function popup(id){
        window.open("${pageContext.request.contextPath}/common/maklumat_hakmilik_single?popup&idSyarat="+id, "eTanah:: Syarat Nyata",
                "status=0,toolbar=0,location=0,menubar=0,width=900,height=300");
    }

    $(document).ready(function() {
        $("#close").click( function(){
            setTimeout(function(){
                self.close();
            }, 100);
        });
    });
</script>


<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.stripes.hasil.PenyediaanPembatalanEndorsanActionbean">
        <s:hidden name="hakmilik.idHakmilik"/>sadasdasdasdasdasd
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Hakmilik </legend>
                <c:if test="${actionBean.negeri eq 'melaka'}">
                    <p>
                        <label>No. Akaun :</label>
                        ${actionBean.hakmilik.akaunCukai.noAkaun}&nbsp;
                    </p>
                </c:if>
                <p>
                    <label>ID Hakmilik :</label>
                    ${actionBean.hakmilik.idHakmilik}&nbsp;
                </p>
                <p>
                    <label>No. Fail :</label>
                    ${actionBean.noFail}&nbsp;
                </p>
            </fieldset>
            <br>
        </div>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik
                </legend>

                <p>
                    <label>Daerah :</label>
                    ${actionBean.hakmilik.daerah.nama}&nbsp;
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp;
                </p>
                <p>
                    <label>Jenis Hakmilik :</label>
                    ${actionBean.hakmilik.kodHakmilik.nama}&nbsp;
                </p>
                <p>
                    <label>Jenis Penggunaan Tanah :</label>
                    ${actionBean.hakmilik.kategoriTanah.nama}&nbsp;
                </p>
                <p>
                    <label>Kod Lot :</label>
                    ${actionBean.hakmilik.lot.nama}&nbsp;

                </p>
                <p>
                    <label>No Lot/PT :</label>
                    ${actionBean.hakmilik.noLot}&nbsp;

                </p>
                <p>
                    <label >Syarat Nyata :</label>
                    <a href="#" onclick="popup('${actionBean.hakmilik.syaratNyata.kod}');return false;">${actionBean.hakmilik.syaratNyata.kod}&nbsp;</a>
                </p>
                <p>
                    <label>No Pelan Piawai :</label>
                    ${actionBean.hakmilik.noLitho}&nbsp;
                </p>
                <p>
                    <label>No Pelan Akui :</label>
                    ${actionBean.hakmilik.noPelan}&nbsp;
                </p>
                <p>
                    <label >Keluasan  :</label>
                    <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}
                </p>
                <p>
                    <label >Cukai Tanah Tahunan (RM)  :</label>

                    <td class="number" align="right"> <fmt:formatNumber value="${actionBean.hakmilik.cukai}" pattern="0.00"/></td>
                </p>
            </fieldset>
        </div>
        <br>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>
                    Maklumat Tuan Tanah
                </legend>

                <div class="content" align="center">

                    <display:table class="tablecloth" name="${actionBean.pihakList}" pagesize="5" cellpadding="0" cellspacing="0"
                                   requestURI="/hasil/endorsan" id="line">
                        <display:column title="Bil" sortable="true" > <div align="center">${line_rowNum}</display:column>
                            <display:column property="pihak.nama" title="Nama" class="cawangan${line_rowNum}" />
                            <display:column property="pihak.noPengenalan" title="No Pengenalan" class="cawangan${line_rowNum}" />
                            <display:column title="Alamat" class="alamat">
                                ${line.pihak.suratAlamat1}<c:if test="${line.pihak.suratAlamat2 ne null}">,</c:if>
                                ${line.pihak.suratAlamat2}<c:if test="${line.pihak.suratAlamat3 ne null}">,</c:if>
                                ${line.pihak.suratAlamat3}<c:if test="${line.pihak.suratAlamat4 ne null}">,</c:if>
                                ${line.pihak.suratAlamat4}<c:if test="${line.pihak.suratPoskod ne null}">,</c:if>
                                ${line.pihak.suratPoskod}<c:if test="${line.pihak.suratNegeri.nama ne null}">,</c:if>
                                ${line.pihak.suratNegeri.nama}
                            </display:column>
                        </display:table>
                    </div>
            </fieldset>
        </div>
        <%--<br>
        <table width="100%">
            <tr>
                <s:hidden name="permohonanUlasan.tarikhUlasan" value="<%=new java.util.Date()%>" />
                <td align="right"><s:button name="" id="close" value="Tutup" class="btn"/></td>
            </tr>
        </table>--%>
    </s:form>
</div>