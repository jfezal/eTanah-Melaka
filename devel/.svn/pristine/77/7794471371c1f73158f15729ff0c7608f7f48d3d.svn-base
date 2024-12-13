<%-- 
    Document   : kertas_ringkasan_mmkn
    Created on : Jun 5, 2013, 2:19:40 PM
    Author     : khadijah
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<style type="text/css">

    #uLabel {
        width: 15em;
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
        var url = '${pageContext.request.contextPath}/hasil/kertas_ringkasan?selectHakmilik&idHakmilik=' + val;
        $.ajax({
            type: "GET",
            url: url,
            dataType: 'html',
            error: function(xhr, ajaxOptions, thrownError) {
                alert("error=" + xhr.responseText);
                doUnBlockUI();
            },
            success: function(data) {
                $('#page_div').html(data);
                doUnBlockUI();
            }
        });
    }

</script>

<s:form beanclass="etanah.view.stripes.hasil.KertasRingkasanActionBean">
    <s:messages/>
    <s:errors/>
    <c:if test="${form1}">
        <s:hidden name="form1" id="form1" value="${form1}"/>
        <s:hidden name="formPtg" id="formPtg" value="false"/>
        <s:hidden name="hakmilikPermohonan.hakmilik.idHakmilik"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend align="center">Ringkasan Permohonan</legend>
                <table width="100%" border="0" align="center">
                    <tr><td colspan="2"><b>Tajuk</b>: </td></tr>
                    <tr><td colspan="2"><s:textarea name="tajuk" rows="4" style="width:97%;"/> </td></tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>Risalat MMKN</b>:</td>
                        <td>
                            ${actionBean.permohonan.idPermohonan}
                        </td>
                    </tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>No. Ruj PTG</b>:</td><td>
                            ${actionBean.permohonan.idPermohonan}
                        </td>
                    </tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>Pemohon:</b></td><td>
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">
                                <c:out value="${senarai.pihak.nama}"/> (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)  <br/>
                                Warganegara <c:out value="${senarai.pihak.wargaNegara.nama}"/>
                                <c:set value="${count + 1}" var="count"/>
                            </c:forEach>&nbsp;</td></tr>

                    <tr><td colspan="2" style="text-align:center;">
                            <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 1}">
                                <br/>
                                <font size="2" color="red">
                                <b>Permohonan Melibatkan Banyak Hakmilik:</b>
                                </font>
                            </c:if>
                            <br/> <b>Hakmilik :</b>
                            <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                    </s:option>
                                </c:forEach>
                            </s:select>
                            <br/><br/>
                        </td></tr>

                    <tr><td><b>Lot/PT:</b></td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot}
                        </td>
                    </tr>

                    <tr><td><b>Bandar/Pekan/Mukim:</b></td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                        </td>
                    </tr>

                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"> <b>Keputusan terdahulu:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="keputusanDulu" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"><b>Ulasan YB Kew.:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="ulasanYb" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"><b>Perakuan PTD:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="perakuanPtd" rows="4" style="width:97%;" class="normal_text"/></td></tr>

                </table>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
    <c:if test="${formPtg}">
        <s:hidden name="form1" id="form1" value="false"/>
        <s:hidden name="formPtg" id="formPtg" value="${formPtg}"/>
        <div class="subtitle">
            <fieldset class="aras1">
                <legend align="center">Ringkasan Permohonan</legend>
                <table width="100%" border="0" align="center">
                    <tr><td colspan="2"><b>Tajuk</b>: </td></tr>
                    <tr><td colspan="2"><s:textarea name="tajuk" rows="4" style="width:97%;"/> </td></tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>Risalat MMKN</b>:</td>
                        <td>
                            ${actionBean.permohonan.idPermohonan}
                        </td>
                    </tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>No. Ruj PTG</b>:</td><td>
                            ${actionBean.permohonan.idPermohonan}
                        </td>
                    </tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td colspan="2"></td>&nbsp;</tr>
                    <tr><td><b>Pemohon:</b></td><td>
                            <c:set value="1" var="count"/>
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">
                                <c:out value="${senarai.pihak.nama}"/> (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)  <br/>
                                Warganegara <c:out value="${senarai.pihak.wargaNegara.nama}"/>
                                <c:set value="${count + 1}" var="count"/>
                            </c:forEach>&nbsp;</td></tr>

                    <tr><td colspan="2" style="text-align:center;">
                            <c:if test="${fn:length(actionBean.permohonan.senaraiHakmilik) > 1}">
                                <br/>
                                <font size="2" color="red">
                                <b>Permohonan Melibatkan Banyak Hakmilik:</b>
                                </font>
                            </c:if>
                            <br/> <b>Hakmilik :</b>
                            <s:select name="idHakmilik" onchange="reload(this.value);" id="hakmilik">
                                <c:forEach items="${actionBean.permohonan.senaraiHakmilik}" var="item" varStatus="line">
                                    <s:option value="${item.hakmilik.idHakmilik}" style="width:400px">
                                        ${item.hakmilik.idHakmilik} ( ${item.hakmilik.daerah.nama} - ${item.hakmilik.bandarPekanMukim.nama} )
                                    </s:option>
                                </c:forEach>
                            </s:select>
                            <br/><br/>
                        </td></tr>

                    <tr><td><b>Lot/PT:</b></td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot}
                        </td>
                    </tr>

                    <tr><td><b>Bandar/Pekan/Mukim:</b></td><td>
                            ${actionBean.hakmilikPermohonan.hakmilik.bandarPekanMukim.nama}
                        </td>
                    </tr>

                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"> <b>Keputusan terdahulu:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="keputusanDulu" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"><b>Ulasan YB Kew.:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="ulasanYb" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"><b>Perakuan PTD:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="perakuanPtd" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                    <tr><td colspan="2">&nbsp;</td></tr>
                    <tr><td colspan="2"><b>Perakuan PTG Melaka:</b></td></tr>
                    <tr><td colspan="2"><s:textarea name="perakuanPtg" rows="4" style="width:97%;" class="normal_text"/></td></tr>
                </table>
                <p>
                    <label>&nbsp;</label>
                    <s:button name="simpanPTG" id="save" value="Simpan PTG" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </fieldset>
        </div>
    </c:if>
</s:form>
