<%--
    Document   : testing1
    Created on : July 23, 2010, 12:40:01 PM
    Author     : Sreenivasa Reddy Munagala.
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<style type="text/css">
    
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<s:form beanclass="etanah.view.strata.RTHSMaklumatTanahActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Tanah
            </legend>
            <p>
                <label>ID Hakmilik :</label>
                <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilik.idHakmilik}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Daerah :</label>
                <c:if test="${actionBean.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilik.daerah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Seksyen :</label>
                <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Bandar/Pekan/Mukim :</label>
                <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Jenis Hakmilik :</label>
                <c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Kategori Tanah :</label>
                <c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Kod Lot/PT :</label>
                <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.lot.nama eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Nombor Lot/PT :</label>
                <c:if test="${actionBean.hakmilik.noLot ne null}"> ${actionBean.hakmilik.noLot}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.noLot eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Keluasan  :</label>
                <c:if test="${actionBean.hakmilik.luas ne null}">  <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Syarat Nyata :</label>
                <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">  ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>

            <p>
                <label>Sekatan Kepentingan :</label>
            </p>
            <table border="0">
                <tr>
                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>
                    </td>
                </tr>
            </table>

            <p>
                <label>Nombor Pelan Piawai :</label>
                <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.noLitho eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Nombor Pelan :</label>
                <c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.noPelan eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Jenis Rizab :</label>
                <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada Data </c:if>
            </p>
             <p>
                <label>Tarikh Didaftarkan :</label>
                <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>  </c:if>
                <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Tempoh Pajakan :</label>
                <c:if test="${actionBean.hakmilik.tempohPegangan ne null}">${actionBean.hakmilik.tempohPegangan} Tahun</c:if>
                <c:if test="${actionBean.hakmilik.tempohPegangan eq null}"> Tiada Data </c:if>
            </p>
            <p>
                <label>Tarikh Luput Pajakan :</label>
                <c:if test="${actionBean.hakmilik.tarikhLuput ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/></c:if>
                <c:if test="${actionBean.hakmilik.tarikhLuput eq null}"> Tiada Data </c:if>
            </p>
        </fieldset>

    </div>

</s:form>