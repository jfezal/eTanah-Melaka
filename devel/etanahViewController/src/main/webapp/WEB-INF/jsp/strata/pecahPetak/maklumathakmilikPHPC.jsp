<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<script type="text/javascript" src="<%= request.getContextPath()%>/pub/scripts/tablecloth.js"></script>
<script type="text/javascript">

    function doEdit() {
        window.open('${pageContext.request.contextPath}/common/maklumat_hakmilik_permohonan?editHakmilikPermohonan', '.:eTanah:.',
        'status=0,toolbar=0,location=0,menubar=0,width=900,height=600');
    }
  
    function p(val){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/strata/maklumat_hakmilik_phpc?showHakmilik&idHakmilik='+val;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            $.unblockUI();
        },'html');
    }
    function pb(val){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/strata/maklumat_hakmilik_phpc?showPemilik&idHakmilik='+val;
        $.get(url,
        function(data){
            $('#page_div').html(data);
            $.unblockUI();
        },'html');

    }
</script>
<div class="subtitle displaytag">
    <s:form beanclass="etanah.view.strata.MaklumatHakmilikPHPCActionBean">
        <div class="subtitle ">
            <s:hidden name="idHakmilik" value="${line.hakmilik.idHakmilik}"/>

            <fieldset class="aras1">
                <legend>
                    Maklumat Hakmilik Permohonan
                </legend>
                <div class="content" align="center">
                    <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}"  cellpadding="0" cellspacing="0"
                                   requestURI="/strata/maklumat_hakmilik_phpc" id="line">
                        <display:column title="Bil">${line_rowNum}</display:column>
                        <display:column title="ID Hakmilik"><a href="#" title="Sila klik untuk perincian maklumat" name="showHakmilik" onclick="p('${line.hakmilik.idHakmilik}');return false;">${line.hakmilik.idHakmilik}</a></display:column>
                        <display:column property="hakmilik.noLot" title="No Lot" />
                        <%--<display:column property="hakmilik.luas" title="Luas" />--%>
                        <display:column title="Luas"><fmt:formatNumber pattern="#,##0.0000" value="${line.hakmilik.luas}"/>&nbsp;${line.hakmilik.kodUnitLuas.nama}</display:column>
                        <display:column property="hakmilik.daerah.nama" title="Daerah" class="daerah"/>
                        <display:column property="hakmilik.bandarPekanMukim.nama" title="Bandar/Pekan/Mukim" />
                        <%--display:column title="Kemaskini">
                                    <p align="center">
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif'
                                             onclick="doEdit('${line.hakmilik.idHakmilik}');return false;"
                                             onmouseover="this.style.cursor='pointer';">
                                    </p>
                        </display:column--%>
                    </display:table>  
                    <br>
                </div>
                <%--p>
               <label>&nbsp;</label>
              <s:button name="" value="K/kini Hakmilik" onclick="doEdit();" class="longbtn"/>
            </p--%>
            </fieldset>
        </div>
        <c:if test="${showHakmilik}">
            <div class="subtitle">
                <fieldset class="aras1">
                    <legend>
                        Maklumat Hakmilik 
                    </legend> 
                    <p>
                        <label>ID Hakmilik :</label>
                        <c:if test="${actionBean.hakmilik.idHakmilik ne null}"> <a href="#" title="Sila klik untuk perincian maklumat" onclick="pb('${actionBean.hakmilik.idHakmilik}');return false;">${actionBean.hakmilik.idHakmilik}</a>
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
                        <c:if test="${actionBean.hakmilik.seksyen.nama eq null}"> Tiada Data </c:if>
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
                    </p>
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
                </fieldset>
            </div>
        </c:if>
        <br>
        <c:if test="${showPemilik}">
            <div class="subtitle ">
                <fieldset class="aras1">
                    <legend>Maklumat Pihak Terlibat</legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.listPemilik}" 
                                       id="line2" cellpadding="0" cellspacing="0" pagesize="10"
                                       requestURI="/strata/maklumat_hakmilik_phpc">
                            <display:column title="Bil" style="text-align: center">${line2_rowNum}</display:column>
                            <display:column property="hakmilik.idHakmilik" title="ID Hakmilik" style="text-align: center"/>            
                            <display:column property="pihak.nama" title="Nama" />            
                            <display:column title="Syer" style="width:40;text-align: center">
                                ${line2.syerPembilang}/${line2.syerPenyebut}
                            </display:column>
                            <display:column property="jenis.nama" title="Jenis Pihak" style="text-align: center"/>
                        </display:table>
                        <br>
                    </div>
                </fieldset>
            </div>
        </c:if>
        <br/>
    </s:form>
</div>
