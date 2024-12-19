<%-- 
    Document   : dev_maklumat_hakmilik_detail
    Created on : Jan 12, 2010, 5:32:06 PM
    Author     : nursyazwani
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>


<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:150px;
        margin-right:0.5em;
        text-align:right;
        width:15em;
        vertical-align:top;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>

<s:form  beanclass="etanah.view.stripes.pembangunan.MaklumatHakmilikActionBean">

     <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>

            <table border="0">
                <tr><td id="tdLabel">ID Hakmilik :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilik.idHakmilik}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> Tiada </c:if>
                    </td>
                </tr>
                
                <tr><td id="tdLabel">No Hakmilik :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noHakmilik ne null}"> ${actionBean.hakmilik.kodHakmilik.kod} <fmt:formatNumber pattern="00" value="${actionBean.hakmilik.noHakmilik}"/>&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.noHakmilik eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Bandar/Pekan/Mukim :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilik.bandarPekanMukim.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                
                <tr><td id="tdLabel">Seksyen :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.seksyen ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU'  and actionBean.stageId ne '8aSedPU'}">
                <tr><td id="tdLabel">Keluasan :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.luas ne null}">  <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada </c:if>
                    </td>
                </tr>
                </c:if>
                <tr><td id="tdLabel">No Syit Piawai :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.noLitho eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Taraf Pegangan :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.pegangan eq 'P'}"> Pajakan </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq 'S'}"> Selama-lamanya </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Tempoh :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.pegangan eq 'P'}">  ${actionBean.hakmilik.tempohPegangan} tahun </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq 'S'}"> Tiada </c:if>
                        <c:if test="${actionBean.hakmilik.pegangan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Tarikh Luput :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.tarikhLuput ne null}"> ${actionBean.hakmilik.tarikhLuput}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <tr><td id="tdLabel">Kawasan Rizab :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Tarikh Daftar :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"> <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU' and actionBean.stageId ne '8aSedPU'}">
                <tr><td id="tdLabel">Kod Lot/PT :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.lot.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Nombor Lot/PT :</td>
                    <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noLot ne null}">
                            ${actionBean.hakmilik.lot.nama} <fmt:formatNumber pattern="00" value="${actionBean.hakmilik.noLot}"/>&nbsp; 
                        </c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">Tiada</c:if>
                    </td>
                </tr>
                </c:if>
                <tr><td id="tdLabel">Tempat :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.lokasi ne null}"> ${actionBean.hakmilik.lokasi}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.lokasi eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Daerah :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilik.daerah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                <c:if test="${actionBean.stageId ne '01TrmArhn' and actionBean.stageId ne 'g_charting_keputusan' and actionBean.stageId ne '03ArhnUtkPnydianPU' and actionBean.stageId ne '04SedPermhnanSBU' and actionBean.stageId ne '05TrmSmkPermhnanSBU' and actionBean.stageId ne '06PrakuanPermhnanSBU' and actionBean.stageId ne '07TrmSBU' and actionBean.stageId ne '08ArhSedPU'  and actionBean.stageId ne '8aSedPU'}">
                 <tr><td id="tdLabel">Nombor Pelan Akui :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.noPelan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                </c:if>
                
<%--
                <tr><td id="tdLabel">Seksyen :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>--%>

              <%--  <tr><td id="tdLabel">Jenis Hakmilik :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                <tr><td id="tdLabel">Cukai Tanah :</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.cukai ne null}"> RM <s:format formatPattern="#,##0.00" value="${actionBean.hakmilik.cukai}"/>&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.cukai eq null}"> Tiada </c:if>
                    </td>
                </tr>
                </c:if>
                <tr><td id="tdLabel">Kategori Tanah :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> Tiada </c:if>
                    </td>
                </tr>
                
                <tr><td id="tdLabel">Syarat Nyata :</td>
                    <td id="tdDisplay"><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">  ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada </c:if>
                    </td>
                </tr>

                <tr><td id="tdLabel">Sekatan Kepentingan :</td>
                    <td id="tdDisplay">  <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada </c:if>
                    </td>
                </tr>
                
            </table>
            <p>
                <label>&nbsp;</label>
                <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            </p>
        </fieldset>

    </div>
</s:form>