<%-- 
    Document   : sedia_draft_warta
    Created on : Nov 16, 2011, 9:46:07 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
    function val1()
    {   if($('#sebab').val() == "")
        {   alert("Sila masukkan Nama Rancangan.");
            return false;
        }
        if($('#permohonanLaporanPelan.tarikhWarta').val() == "")
        {   alert("Sila masukkan Tarikh Kuat Kuasa.");
            return false;
        }
        if($('#permohonanLaporanPelan.catatan').val() == "")
        {   alert("Sila pilih Seksyen.");
            return false;
        }
        return true;
    }
</script>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pelupusan.SediaDraftWartaActionBean">
            <s:messages/>
            <s:hidden name="permohonanLaporanPelan.idMohonlaporKws"  />
            <fieldset class="aras1">
                <legend>Maklumat Draf Warta</legend>
                <div align="center">
                    <table width="50%" border="0">
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><em>*</em>Nama Rancangan:</td>
                            <td colspan="2">
                                <s:textarea name="sebab" class="normal_text" rows="3" cols="50"/>
                            </td>
                        </tr>
                        <c:if test="${actionBean.kodNegeri eq '04'}">
                            <tr>
                                <td>&nbsp;</td>
                                <td>Tarikh Kuat Kuasa:</td>
                                <td colspan="2">
                                    <s:text name="permohonanLaporanPelan.tarikhWarta" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${actionBean.kodNegeri eq '05'}">
                            <%--<c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">--%>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><em>*</em>Tarikh Kuat Kuasa:</td>
                                    <td colspan="2">
                                        <s:text name="permohonanLaporanPelan.tarikhWarta" id="datepicker" class="datepicker" formatPattern="dd/MM/yyyy"/>
                                    </td>
                                </tr>
                            <%--</c:if>--%>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td><em>*</em>Seksyen:</td>
                                    <td colspan="2">
                                        <s:select name="permohonanLaporanPelan.catatan">
                                            <s:option value="">Sila Pilih</s:option>
                                            <s:option value="4">4</s:option>
                                            <s:option value="5">5</s:option>
                                            <s:option value="6">6</s:option>
                                        </s:select>
                                    </td>
                                    <td></td>
                                </tr>
                            </c:if>
                        </c:if>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2">
                                <c:choose>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod ne 'PTGSA'}">
                                <s:button name="sediaDraftWartaSimpan" value="Simpan" class="btn" onclick="if(val1())doSubmit(this.form, this.name, 'page_div')"/>
                                </c:when>
                                <c:when test="${actionBean.permohonan.kodUrusan.kod eq 'PTGSA' and actionBean.stageId eq '25SedDrfWrtPmbtln'}">
                                    <s:button name="sediaDraftWartaSimpan" value="Simpan" class="btn" onclick="if(val1())doSubmit(this.form, this.name, 'page_div')"/>
                                </c:when>
                                <c:otherwise>
                                </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PWGSA'}">
                        <tr>
                            <td colspan="4">
                                <fieldset class="aras1">
                                    <legend>JADUAL</legend>
                                    <div class="content" align="center">
                                        <display:table class="tablecloth" name="${actionBean.mohanHakmilikList}" pagesize="5" requestURI="/pelupusan/sedia_draf_war" id="line">
                                            <display:column title="Daerah"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 100px">${line.permohonan.cawangan.daerah.nama}</font></c:if></display:column>
                                            <display:column title="Mukim"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 150px">${line.bandarPekanMukimBaru.nama}</font></c:if></display:column>
                                            <display:column title="No PW"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 100px">${actionBean.noPW}</font></c:if></display:column>
                                            <%--<display:column title="Nama Rancangan" style="width:500px" > <c:if test="${line.permohonan.sebab ne null}">${line.permohonan.sebab}</c:if></display:column>--%>
                                            <display:column title="Luas"><c:if test="${line.permohonan.sebab ne null}"><font style="text-transform: capitalize; text-align: center; width: 70px"><fmt:formatNumber pattern="#,##0.0000" value="${actionBean.hakmilikPermohonan.luasTerlibat}"/></font></c:if>${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</display:column>
                                        </display:table>
                                        <br/><br/>
                                    </div>
                                </fieldset>
                            </td>
                        </tr>
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PWGSA'}">
                        <tr>
                            <td colspan="4">
                                <fieldset class="aras1">
                                    <legend>JADUAL</legend>
                                    <div class="content" align="center">
                                        <c:if test="${actionBean.kodNegeri eq '04'}">
                                            <display:table class="tablecloth" name="${actionBean.mohanHakmilikList}" pagesize="5" 
                                                           requestURI="/pelupusan/sedia_draf_war"  id="line">
                                                <display:column title="No. Lot Ukur" style="width:300px" property="hakmilik.noLot"></display:column>
                                                <display:column title="Jenis Hakmilik"  style="width:300px" property="hakmilik.noHakmilik"/>
                                                <display:column title="Luas Lot" style="width:300px"  >${line.hakmilik.luas}${line.hakmilik.kodUnitLuas.kod}</display:column>
                                                <display:column title="Bahagian" style="width:300px" ><c:if test="${line.statusLuasDiluluskan eq 'S'}">Sebahagian</c:if>
                                                    <c:if test="${line.statusLuasDiluluskan eq 'P'}">Seluruh</c:if>
                                                    <c:if test="${line.statusLuasDiluluskan eq '' || line.statusLuasDiluluskan eq null}"> </c:if></display:column>
                                            </display:table>
                                        </c:if>
                                        <c:if test="${actionBean.kodNegeri eq '05'}">
                                            <display:table class="tablecloth" name="${actionBean.mohanHakmilikList}" pagesize="5" 
                                                           requestURI="/pelupusan/sedia_draf_war"  id="line">
                                                <display:column title="Luas Lot" style="width:300px"  >${line.hakmilik.luas} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</display:column>
                                                <display:column title="Bahagian" style="width:300px" ><c:if test="${line.statusLuasDiluluskan eq 'S'}">Sebahagian</c:if>
                                                    <c:if test="${line.statusLuasDiluluskan eq 'P'}">Seluruh</c:if>
                                                    <c:if test="${line.statusLuasDiluluskan eq '' || line.statusLuasDiluluskan eq null}"> </c:if></display:column>
                                            </display:table>
                                        </c:if>            
                                    </div>
                                </fieldset>
                            </td>
                        </tr>
                        </c:if>
                    </table>
                </div>
            </fieldset>
        </s:form>
    </body>
</html>
