<%--
    Document   : maklumatTanahPHPP
    Created on : Oct 12, 2012, 10:42:01 AM
    Author     : murali
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/tablecloth.js"></script>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<style type="text/css">
    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:40em;
    }
</style>
<script type="text/javascript">    
    function popUpHakmilik(w){
    <%--alert("popup"+w);--%>
            var url = '${pageContext.request.contextPath}/strata/maklumat_tanah_phpp?showFormPopup&idHakmilik='+w;
            window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1500,height=800");
        }

        function p(v,w){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            $.get("${pageContext.request.contextPath}/strata/maklumat_tanah?showForm&idHakmilikPihakBerkepentingan="+v+"&idHakmilik="+w,
            function(data){
                $("#perincianPihak").show();
                $("#perincianPihak").html(data);
                $.unblockUI();
            });
        }
</script>
<s:form beanclass="etanah.view.strata.MaklumatTanahPHPPActionBean">

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Hakmilik</legend> 
            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'HTSC'}">
                <c:forEach items="${actionBean.hakmilikPermohonanListBaru}" var="line">
                    <c:if test="${line.hakmilik.kodStatusHakmilik.kod eq 'B'}">
                        <p>
                            <label>ID Hakmilik :</label>
                            <c:if test="${line.hakmilik.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="popUpHakmilik('${line.hakmilik.idHakmilik}')">${line.hakmilik.idHakmilik}</a>
                            </c:if>
                            <c:if test="${line.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                        </p>
                    </c:if>
                </c:forEach>
            </c:if>            
            <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod eq 'PHPC'}">
                <p>
                    <label>ID Hakmilik :</label>
                    <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('61200304','${actionBean.hakmilik.idHakmilik}');return false;">${actionBean.hakmilik.idHakmilik}</a>
                    </c:if>
                    <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Daerah :</label>
                    <c:if test="${actionBean.kBpm ne null}"> ${actionBean.kBpm}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Seksyen :</label>
                    <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> &nbsp; </c:if>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.bpm}&nbsp; </c:if>
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
                    <label>Syarat Nyata :</label>
                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">  ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> Tiada Data </c:if>

                <p>
                    <label>Sekatan Kepentingan :</label>
                </p>
                <table border="0">
                    <tr>
                        <td id="tdDisplay">
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null && actionBean.hakmilik.sekatanKepentingan.sekatan ne '-'}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq '-'}">Tiada Data &nbsp;</c:if>
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> Tiada Data </c:if>

                        </td>
                    </tr>
                </table>
                <p>
                    <label>Nombor Skim :</label>
                    <c:if test="${actionBean.hakmilik.noSkim ne null}">${actionBean.hakmilik.noSkim}</c:if>
                    <c:if test="${actionBean.hakmilik.noSkim eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Nombor Bangunan :</label>
                    <c:if test="${actionBean.hakmilik.noBangunan ne null}">${actionBean.hakmilik.noBangunan}</c:if>
                    <c:if test="${actionBean.hakmilik.noBangunan eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Nombor Tingkat :</label>
                    <c:if test="${actionBean.hakmilik.noTingkat ne null}">${actionBean.hakmilik.noTingkat}</c:if>
                    <c:if test="${actionBean.hakmilik.noTingkat eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Nombor Petak :</label>
                    <c:if test="${actionBean.hakmilik.noPetak ne null}">${actionBean.hakmilik.noPetak}</c:if>
                    <c:if test="${actionBean.hakmilik.noPetak eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Luas Petak (meter persegi):</label>
                    <c:if test="${actionBean.hakmilik.luas ne null}">${actionBean.hakmilik.luas}</c:if>
                    <c:if test="${actionBean.hakmilik.luas eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Unit Syer Petak :</label>
                    <c:if test="${actionBean.hakmilik.unitSyer ne null}">${actionBean.hakmilik.unitSyer}</c:if>
                    <c:if test="${actionBean.hakmilik.unitSyer eq null}"> Tiada Data </c:if>
                </p>
            </c:if>


            <c:if test="${actionBean.permohonan.permohonanSebelum.kodUrusan.kod ne 'PHPC' && actionBean.permohonan.kodUrusan.kod ne 'PHPP' && actionBean.permohonan.permohonanSebelum.kodUrusan.kod ne 'PHPP'}">
                <c:if test="${actionBean.kodNegeri eq '04'}">
                <p>
                    <label>ID Hakmilik :</label>
                    <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('61200304','${actionBean.hakmilik.idHakmilik}');return false;">${actionBean.hakmilik.idHakmilik}</a>
                    </c:if>
                    <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Daerah :</label>
                    <c:if test="${actionBean.kBpm ne null}"> ${actionBean.kBpm}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> Tiada Data </c:if>
                </p>
                <p>
                    <label>Seksyen :</label>
                    <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> &nbsp; </c:if>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.bpm}&nbsp; </c:if>
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
                        <td id="tdDisplay">
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null && actionBean.hakmilik.sekatanKepentingan.sekatan ne '-'}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq '-'}">Tiada Data &nbsp;</c:if>
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
                </c:if>
                
               <c:if test="${actionBean.kodNegeri eq '05'}">
                <p>
                    <label>ID Hakmilik :</label>
                    <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="p('61200304','${actionBean.hakmilik.idHakmilik}');return false;">${actionBean.hakmilik.idHakmilik}</a>
                    </c:if>
                    <c:if test="${actionBean.hakmilik.idHakmilik eq null}"> - </c:if>
                </p>
                <p>
                    <label>Daerah :</label>
                    <c:if test="${actionBean.kBpm ne null}"> ${actionBean.kBpm}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.daerah.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Seksyen :</label>
                    <c:if test="${actionBean.hakmilik.seksyen.nama ne null}"> ${actionBean.hakmilik.seksyen.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Bandar/Pekan/Mukim :</label>
                    <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}"> ${actionBean.bpm}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Jenis Hakmilik :</label>
                    <c:if test="${actionBean.hakmilik.kodHakmilik.nama ne null}"> ${actionBean.hakmilik.kodHakmilik.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.kodHakmilik.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Kategori Tanah :</label>
                    <c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}"> ${actionBean.hakmilik.kategoriTanah.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Kod Lot/PT :</label>
                    <c:if test="${actionBean.hakmilik.lot.nama ne null}"> ${actionBean.hakmilik.lot.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.lot.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Nombor Lot/PT :</label>                                        
                        <c:if test="${actionBean.nLot ne null}"> ${actionBean.nLot}&nbsp; </c:if>
                        <c:if test="${actionBean.nLot eq null}"> - </c:if>                     
                </p>
                <p>
                    <label>Keluasan  :</label>
                    <c:if test="${actionBean.hakmilik.luas ne null}">  <s:format formatPattern="#,##0.0000" value="${actionBean.hakmilik.luas}"/>&nbsp; ${actionBean.hakmilik.kodUnitLuas.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.luas eq null}"> - </c:if>
                </p>
                <p>
                    <label>Syarat Nyata :</label>
                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">  ${actionBean.hakmilik.syaratNyata.syarat}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}"> - </c:if>

                <p>
                    <label>Sekatan Kepentingan :</label>
                </p>
                <table border="0">
                    <tr>
                        <td id="tdDisplay">
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null && actionBean.hakmilik.sekatanKepentingan.sekatan ne '-'}">${actionBean.hakmilik.sekatanKepentingan.sekatan}&nbsp;</c:if>
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq '-'}"> - &nbsp;</c:if>
                            <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}"> - </c:if>

                        </td>
                    </tr>
                </table>
                <p>
                    <label>Nombor Pelan Piawai :</label>
                    <c:if test="${actionBean.hakmilik.noLitho ne null}"> ${actionBean.hakmilik.noLitho}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.noLitho eq null}"> - </c:if>
                </p>
                <p>
                    <label>Nombor Pelan :</label>
                    <c:if test="${actionBean.hakmilik.noPelan ne null}"> ${actionBean.hakmilik.noPelan}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.noPelan eq null}"> - </c:if>
                </p>
                <p>
                    <label>Jenis Rizab :</label>
                    <c:if test="${actionBean.hakmilik.rizab.nama ne null}"> ${actionBean.hakmilik.rizab.nama}&nbsp; </c:if>
                    <c:if test="${actionBean.hakmilik.rizab.nama eq null}"> - </c:if>
                </p>
                <p>
                    <label>Tarikh Didaftarkan :</label>
                    <c:if test="${actionBean.hakmilik.tarikhDaftar ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhDaftar}"/>  </c:if>
                    <c:if test="${actionBean.hakmilik.tarikhDaftar eq null}"> - </c:if>
                </p>
                <p>
                    <label>Tempoh Pajakan :</label>
                    <c:if test="${actionBean.hakmilik.tempohPegangan ne null}">${actionBean.hakmilik.tempohPegangan} Tahun</c:if>
                    <c:if test="${actionBean.hakmilik.tempohPegangan eq null}"> - </c:if>
                </p>
                <p>
                    <label>Tarikh Luput Pajakan :</label>
                    <c:if test="${actionBean.hakmilik.tarikhLuput ne null}"><fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.hakmilik.tarikhLuput}"/></c:if>
                    <c:if test="${actionBean.hakmilik.tarikhLuput eq null}"> - </c:if>
                </p>
                </c:if>
            </c:if>
        </fieldset>
    </div>
    <div id="perincianPihak">
        <p></p>
    </div>
</s:form>