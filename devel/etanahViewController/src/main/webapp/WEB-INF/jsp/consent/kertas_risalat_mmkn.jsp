<%-- 
    Document   : maklumat_risalat_mmkn
    Created on : Mar 25, 2011, 5:26:47 PM
    Author     : muhammad.amin
--%>


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 30em;
        float: left;
        text-align: left;
        margin-right: 0px;
        display: block;
        color:#003194;
        font-weight: bold;
        font-family:Tahoma;
        font-size: 13px;
        margin-left: -3px;
    }
</style>

<script type="text/javascript">
    
    function reload(val) {
        doBlockUI();
        var url = '${pageContext.request.contextPath}/consent/kertas_risalat_mmkn?selectHakmilik&idHakmilik=' + val;
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function (xhr, ajaxOptions, thrownError){
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success : function(data){
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }
           
</script>

<s:form beanclass="etanah.view.stripes.consent.KertasRisalatMmknActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan eq null}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend></legend>
                <br/><br/>
                <p align="center"><font size="4"> Sila masukkan maklumat rayuan pengurangan bayaran terlebih dahulu.</font></p>
                <br/><br/><br/><br/>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${(actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan ne null) || (actionBean.fasaPermohonan eq null && actionBean.mohonUrusanRayuan eq null)}">
        <div class="subtitle">
            <fieldset class="aras1">
                <legend>Maklumat Kertas Risalat</legend>
                <table width="100%" border="0">
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">TAJUK KERTAS</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="tajuk" rows="4" style="width:97%;"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.tajuk eq ' '}">Tiada Data</c:if>
                                <c:if test="${actionBean.tajuk ne ' '}">${actionBean.tajuk}</c:if>
                            </c:if>
                        </td></tr>

                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">1. TUJUAN</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="tujuan" rows="5" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.tujuan eq ' ' || actionBean.tujuan eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.tujuan ne ' '}">
                                    <s:textarea name="tujuan"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                </c:if>
                            </c:if></td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">2. LATAR BELAKANG</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="latarBelakang" rows="10" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.latarBelakang eq ' ' || actionBean.latarBelakang eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.latarBelakang ne ' '}">
                                    <s:textarea name="latarBelakang"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if></td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">3. BUTIRAN HAKMILIK</td></tr>

                    <tr><td colspan="2" style="text-align:center;">
                            <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 1}">
                                <br/> 
                                <font size="2" color="red">
                                <b>Permohonan Melibatkan Banyak Hakmilik</b>
                                </font>
                            </c:if>
                            <br/> Hakmilik : 
                            <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                    </s:option>
                                </c:forEach>
                            </s:select>
                            <br/><br/>
                        </td></tr>

                    <tr><td></td><td>
                            <table border="1">
                                <tr><td>3.1 Jenis dan No. Hakmilik :</td><td>
                                        ${actionBean.hakmilikPermohonan.hakmilik.kodHakmilik.kod} ${actionBean.hakmilikPermohonan.hakmilik.noHakmilik}
                                    </td></tr>
                                <tr><td>3.2 No. Lot/PT :</td><td>
                                        ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot}
                                    </td></tr>
                                <tr><td>3.3 Mukim :</td><td>  
                                        ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                                    </td></tr>
                                <tr><td> 3.4 Luas :</td><td>
                                        ${actionBean.hakmilikPermohonan.hakmilik.luas} ${actionBean.hakmilikPermohonan.hakmilik.kodUnitLuas.nama}
                                    </td></tr> <tr><td>  3.5 Pegangan :</td><td>
                                    <c:if test="${actionBean.hakmilikPermohonan.hakmilik.pegangan eq 'P'}">  
                                                <c:out value="${actionBean.hakmilikPermohonan.hakmilik.tempohPegangan}"/>&nbsp;<br/>
                                        </c:if>   
                                        <c:if test="${actionBean.hakmilikPermohonan.hakmilik.pegangan eq 'S'}">  
                                            Selama-lamanya&nbsp;<br/>
                                        </c:if> 
                                    </td></tr> <tr><td> 3.6 Tarikh luput pajakan :</td><td>  

                                        <c:if test="${actionBean.hakmilikPermohonan.hakmilik.pegangan eq 'P'}">  
                                            <s:format formatPattern="dd/MM/yyyy" value="${actionBean.hakmilikPermohonan.hakmilik.tarikhLuput}"/>&nbsp;<br/>
                                        </c:if>
                                        <c:if test="${actionBean.hakmilikPermohonan.hakmilik.pegangan eq 'S'}">  
                                            -&nbsp;<br/>
                                        </c:if> 
                                    </td></tr> <tr><td> 3.7 Kategori :</td><td>
                                    ${actionBean.hakmilikPermohonan.hakmilik.kategoriTanah.nama}
                                    </td></tr> <tr><td> 3.8 Syarat Nyata :</td><td>
                                    ${actionBean.hakmilikPermohonan.hakmilik.syaratNyata.syarat}
                                    </td></tr> <tr><td>   3.9 Sekatan Kepentingan :</td><td>
                                    ${actionBean.hakmilikPermohonan.hakmilik.sekatanKepentingan.sekatan}
                                    </td></tr> <tr><td>  3.10 Tuan Tanah Berdaftar :</td><td>
                                    <c:forEach items="${actionBean.hakmilikPermohonan.hakmilik.senaraiPihakBerkepentingan}" var="senarai">   
                                        <c:if test="${senarai.jenis.kod eq 'PM' && senarai.aktif eq 'Y'}">  
                                                    <c:out value="${senarai.pihak.nama}"/>&nbsp;&nbsp;(<c:out value="${senarai.syerPembilang}"/>/<c:out value="${senarai.syerPenyebut}"/>&nbsp;bahagian )&nbsp;(<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>&nbsp;<c:out value="${senarai.pihak.noPengenalan}"/>)<c:if test="${senarai.pihak.wargaNegara ne null}"> &nbsp;(<c:out value="${senarai.pihak.wargaNegara.nama}"/>)</c:if><br/>
                                            </c:if>
                                        </c:forEach>
                                    </td></tr>
                            </table>
                        </td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">4. MAKLUMAT PERMILIKAN HARTANAH DI MALAYSIA </td></tr>
                    <tr><td></td>
                        <td>   
                            <c:if test="${fn:length(actionBean.senaraiTanahLain) == 0}">
                                <br/>
                                <font color="black" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;TIADA MAKLUMAT PEMILIKAN HARTANAH DI MALAYSIA</font>
                                <br/>
                            </c:if>
                            <c:if test="${fn:length(actionBean.senaraiTanahLain) > 0}">
                                <div class="content" align="center">
                                    <br>
                                    Maklumat lain-lain tanah yang dimiliki
                                    <display:table name="${actionBean.senaraiTanahLain}" id="lineTanah" class="tablecloth" >
                                        <display:column title="Bil" sortable="true">${lineTanah_rowNum}</display:column>
                                        <display:column property="hakmilik.idHakmilik" title="ID Hakmilik"/>
                                        <c:if test="${lineTanah.hakmilik.noLot eq 'Tiada' || lineTanah.hakmilik.noLot  eq 'X'}">
                                            <display:column property="hakmilik.noLot" title="Nombor Lot/PT" style="vertical-align:top"/>
                                        </c:if>
                                        <c:if test="${lineTanah.hakmilik.noLot ne 'Tiada' && lineTanah.hakmilik.noLot  ne 'X'}">
                                            <display:column title="Nombor Lot/PT" style="vertical-align:top"><fmt:parseNumber value="${lineTanah.hakmilik.noLot}"/></display:column>
                                        </c:if>
                                        <display:column title="Luas"  style="vertical-align:top"><fmt:formatNumber pattern="#,##0.0000" value="${lineTanah.hakmilik.luas}"/>&nbsp;<font style="text-transform:uppercase;">${lineTanah.hakmilik.kodUnitLuas.nama}</font></display:column>
                                        <display:column title="Daerah" class="daerah" style="vertical-align:top"><font style="text-transform:uppercase;">${lineTanah.hakmilik.daerah.nama}</font></display:column>
                                        <display:column title="Bandar/Pekan/Mukim" style="vertical-align:top"><font style="text-transform:uppercase;">${lineTanah.hakmilik.bandarPekanMukim.nama}</font></display:column>
                                        <display:column title="Syer Dimiliki" >
                                            <div align="center">
                                                ${lineTanah.syerPembilang}/${lineTanah.syerPenyebut}
                                            </div>
                                        </display:column>
                                    </display:table>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                    <c:if test="${(actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan ne null)}">
                        <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;</td><td id="uLabel">5. ALASAN RAYUAN</td></tr>
                        <tr><td></td><td>
                                <c:if test="${tajuk}">
                                    <s:textarea name="alasanRayuan" rows="10" style="width:97%;"  class="normal_text"/>
                                </c:if>
                                <c:if test="${viewTajuk}">
                                    <c:if test="${actionBean.alasanRayuan eq ' ' || actionBean.alasanRayuan eq null}">Tiada Data</c:if>
                                    <c:if test="${actionBean.alasanRayuan ne ' '}">
                                        <s:textarea name="alasanRayuan"  readonly="true" class="normal_text"
                                                    style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                    </c:if>
                                </c:if></td>
                        </tr>
                    </c:if>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">
                            <c:if test="${(actionBean.fasaPermohonan eq null && actionBean.mohonUrusanRayuan eq null)}">5.</c:if>
                            <c:if test="${(actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan ne null)}">6.</c:if>

                            ASAS-ASAS PERTIMBANGAN</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="asas" rows="10" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.asas eq ' ' || actionBean.asas eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.asas ne ' '}">
                                    <s:textarea name="asas"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if></td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                    <tr><td>&nbsp;</td><td id="uLabel">
                            <c:if test="${(actionBean.fasaPermohonan eq null && actionBean.mohonUrusanRayuan eq null)}">6.</c:if>
                            <c:if test="${(actionBean.fasaPermohonan ne null && actionBean.mohonUrusanRayuan ne null)}">7.</c:if>
                            PERAKUAN PENGARAH TANAH DAN GALIAN</td></tr>
                    <tr><td></td><td>
                            <c:if test="${tajuk}">
                                <s:textarea name="perakuanPtg" rows="10" style="width:97%;"  class="normal_text"/>
                            </c:if>
                            <c:if test="${viewTajuk}">
                                <c:if test="${actionBean.perakuanPtg eq ' ' || actionBean.ulasanPtg eq null}">Tiada Data</c:if>
                                <c:if test="${actionBean.perakuanPtg ne ' '}">
                                    <s:textarea name="perakuanPtg"  readonly="true" class="normal_text"
                                                style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                                </c:if>
                            </c:if></td></tr>
                    <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                </table>
                <c:if test="${tajuk}">
                    <p>
                        <label>&nbsp;</label>
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </fieldset>
            <br/>
        </div>
    </c:if>
</s:form>
