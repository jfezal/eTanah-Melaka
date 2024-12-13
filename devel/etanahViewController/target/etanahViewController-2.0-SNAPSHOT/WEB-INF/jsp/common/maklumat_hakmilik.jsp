<%--
    Document   : maklumat_hakmilik
    Created on : 08 Oktober 2009, 3:41:04 PM
    Author     : khairil
--%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/maxwindow.js"></script>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
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
<script type="text/javascript">
    $(document).ready(function() {
        alert('test');
        maximizeWindow();
    });
</script>

<s:form  beanclass="etanah.view.stripes.common.MaklumatPermohonanActionBean">
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Hakmilik
            </legend>
            <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel">ID Hakmilik :&nbsp;</td>
                    <td id="tdDisplay"> <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> ${actionBean.hakmilik.idHakmilik} </c:if>
                        <c:if test="${actionBean.hakmilik.idHakmilik eq null}">&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td id="tdLabel">Daerah :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.daerah.nama ne null}"> ${actionBean.hakmilik.daerah.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td id="tdLabel">Seksyen :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>

                    <tr><td id="tdLabel">Bandar/Pekan/Mukim :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.hakmilik.bandarPekanMukim.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Jenis Hakmilik :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Kategori Tanah :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Kod Lot/PT :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.lot.nama eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Nombor Lot/PT :&nbsp;</td>
                        <td id="tdDisplay">

                        <c:if test="${actionBean.hakmilik.noLot ne null}">
                            <c:if test="${actionBean.hakmilik.noLot eq 'Tiada' || actionBean.hakmilik.noLot  eq 'X'}">
                                ${actionBean.hakmilik.noLot}&nbsp;
                            </c:if>
                            <c:if test="${actionBean.hakmilik.noLot ne 'Tiada' && actionBean.hakmilik.noLot  ne 'X'}">
                                <fmt:parseNumber value="${actionBean.hakmilik.noLot}"/>&nbsp;
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Keluasan :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.luas ne null}"> <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama} </c:if>
                        <c:if test="${actionBean.hakmilik.luas eq null}">&nbsp;</c:if>
                        </td>
                    </tr>  
                    <tr><td id="tdLabel">Syarat Nyata :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}"> ${actionBean.hakmilik.syaratNyata.syarat} </c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Sekatan Kepentingan :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}"> ${actionBean.hakmilik.sekatanKepentingan.sekatan} </c:if>
                        <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Nombor Pelan Piawai :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho} </c:if>
                        <c:if test="${actionBean.hakmilik.noLitho eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Nombor Pelan :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan} </c:if>
                        <c:if test="${actionBean.hakmilik.noPelan eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Jenis Rizab :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${fn:length(actionBean.senaraiUrusanRizab) > 0}">
                            <c:forEach items="${actionBean.senaraiUrusanRizab}" var="urusan">                           
                                <c:if test="${urusan.kodUrusan.kod eq 'IGSA'}">PENGISYTIHARAN TANAH BERKELOMPOK<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IGSA5'}">PENGISYTIHARAN TANAH BERKELOMPOK, PENEMPATAN DESA<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IGSA6'}">PPENGISYTIHARAN TANAH BERKELOMPOK, PENEMPATAN BANDAR<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'ITP'}">PENGISYTIHARAN TANAH PEKAN<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'ITD'}">PENGISYTIHARAN TANAH DESA<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'ITB'}">PENGISYTIHARAN TANAH BANDAR<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IRTB'}">PENGISYTIHARAN RIZAB TANAH BUKIT<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IPM'}">TANAH SIMPANAN MELAYU<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'PTP'}">PENGISYTIHARAN TANAH PEKAN<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'KB'}">KAWASAN TANAH BUKIT<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IRM'}">DALAM KAWASAN RIZAB MELAYU<br/></c:if>
                                <c:if test="${urusan.kodUrusan.kod eq 'IROA'}">PENGISYTIHARAN KAWASAN ORANG ASLI<br/></c:if>                               
                            
                        </c:forEach> 
                        </c:if>   
                        <c:if test="${fn:length(actionBean.senaraiUrusanRizab) == 0}"> Tiada</c:if>
                          
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Cukai Tahunan (RM) :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.cukai ne null}"><s:format formatPattern="##,##0.00" value="${actionBean.hakmilik.cukai}"/> </c:if>
                        <c:if test="${actionBean.hakmilik.cukai eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                    <tr><td id="tdLabel">Lokasi :&nbsp;</td>
                        <td id="tdDisplay">
                        <c:if test="${actionBean.hakmilik.lokasi ne null}"> ${actionBean.hakmilik.lokasi} </c:if>
                        <c:if test="${actionBean.hakmilik.lokasi eq null}">&nbsp;</c:if>
                        </td>
                    </tr>
                <c:if test="${fn:length(actionBean.senaraiHakmilikUrusan) > 0}">
                    <tr><td>&nbsp;</td>
                        <td>
                            <b> TANAH ADAT </b>
                        </td>
                    </tr>
                </c:if>
            </table>
            <!--            <p align="center">
                            <br>
            <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
            <br>
        </p>-->
        </fieldset>
    </div>

    <c:if test="${actionBean.flagBayar}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Bayaran Cukai</legend>
                <table border="0">
                <tr> <td>&nbsp;</td></tr>
                <tr><td id="tdLabel">Nombor Resit :&nbsp;</td>
                    <td id="tdDisplay"> ${actionBean.dokumenKewangan.idDokumenKewangan}&nbsp;</td>
                    </tr>

                    <tr><td id="tdLabel">Amaun Bayaran (RM) :&nbsp;</td>
                        <td id="tdDisplay"><fmt:formatNumber value="${actionBean.dokumenKewangan.amaunBayaran}" pattern="#,###,###,##0.00" />&nbsp;</td>
                    </tr>

                    <tr><td id="tdLabel">Tarikh Bayaran :&nbsp;</td>
                        <td id="tdDisplay"> <fmt:formatDate type="date" pattern="dd/MM/yyyy hh:MM:SS a" value="${actionBean.dokumenKewangan.infoAudit.tarikhMasuk}"/>&nbsp;</td>
                    </tr>
                </table>
            </fieldset>
        </div>
    </c:if>
    <p align="center">
        <br>
        <s:button name="" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>
        <br>
    </p>

</s:form>