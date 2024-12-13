<%-- 
    Document   : syaratPBMT
    Created on : Mar 13, 2013, 4:09:44 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
        <s:hidden name="idMh" id="idMh" value="${actionBean.hakmilikPermohonan.id}"/>
        <c:choose>
            <c:when test="${actionBean.kodNegeri eq '04'}">
                <table class="tablecloth" border="0">
                    <tr>
                        <td>
                            Luas Disyorkan :
                        </td>
                        <td>
                            <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Luas Diluluskan :
                        </td>
                        <td>
                            <s:text name="hakmilikPermohonan.luasDiluluskan" id="luasLulus" formatPattern="#,###,##0.0000"/> 
                            ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                            <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                        </td>
                    </tr>
                    <tr>
                        <td>Kategori</td>
                        <td> 
                            <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                                <s:option value="">Sila Pilih</s:option>
                                <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Kegunaan Tanah</td>
                        <td id="partialKodGunaTanah"></td>
                    </tr>
                    <tr>
                        <td>Jenis Hakmilik :</td>
                        <td>
                            <s:select name="kodHakmilik" id="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onchange="doSomething(this.value);" >
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="GRN">Geran (Pendaftar)</s:option>
                                <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                            </s:select>
                        </td>
                    </tr>
                    <tr id="jikaPajakan">
                        <td>Tempoh Pajakan:</td>
                        <td>
                            <s:select name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan">
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="30"> 30 </s:option>
                                <s:option value="60"> 60 </s:option>
                                <s:option value="99"> 99 </s:option>
                            </s:select>
                        </td>
                    </tr>                              
                    <tr>
                        <td>Premium : </td>
                        <td>
                            <s:select name="keteranganKadarPremium" value="${actionBean.hakmilikPermohonan.keteranganKadarPremium}" id="nama" onchange="javaScript:showPremimTxt(this.value)">
                                <s:option value="">--Sila Pilih--</s:option>
                                <s:options-collection collection="${actionBean.senaraikodKadarPremium}" />
                            </s:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Hasil (RM) :</td>
                        <td>
                            <s:text name="hakmilikPermohonan.keteranganCukaiBaru" size="10"/>
                            <s:select name="jenishasil" id="jenishasil">
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="1"> Bagi setiap 100 meter persegi (Bangunan) </s:option>
                                <s:option value="2"> Kurang 5 Hektar (Pertanian) </s:option>
                                <s:option value="3"> Lebih 5 Hektar (Pertanian) </s:option>
                            </s:select> 
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Upah Ukur :
                        </td>
                        <td>
                            <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                            <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                        </td>
                    </tr>
                </table>
            <legend>Syarat</legend>
            <table class="tablecloth" align="center">
                <tr>
                    <td>
                        Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                    </td>
                </tr>
            </table>            
        </c:when>
        <c:when test="${actionBean.kodNegeri eq '05'}">
            <table class="tablecloth" border="0">
                <c:if test="${actionBean.kelompok eq false}">
                <tr>
                    <td>
                        Luas Disyorkan :
                    </td>
                    <td>
                        <s:format value="${actionBean.hakmilikPermohonan.luasTerlibat}" formatPattern="#,###,##0.0000"/> ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                    </td>
                </tr>
                <tr>
                    <td>
                        Luas Diluluskan :
                    </td>
                    <td>
                        <s:text name="hakmilikPermohonan.luasDiluluskan" formatPattern="#,###,##0.0000"/> 
                        ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}
                        <s:hidden name="kodU" id="kodU" value="${actionBean.hakmilikPermohonan.kodUnitLuas.kod}"/>

                    </td>
                </tr>
                </c:if>
                <tr>
                    <td>Kategori</td>
                    <td> 
                        <s:select name="kategoriTanahBaru.kod" id="_kodKatTanah" value="${actionBean.hakmilikPermohonan.kategoriTanahBaru.kod}" onchange="filterKodGunaTanah();"> 
                            <s:option value="">Sila Pilih</s:option>
                            <s:options-collection collection="${listUtil.senaraiKodKategoriTanah}" label="nama" value="kod" />
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td>Kegunaan Tanah</td>
                    <td id="partialKodGunaTanah"></td>
                </tr>
                <tr>
                        <td>Jenis Hakmilik :</td>
                        <td>
                            <s:select name="kodHakmilik" id="kodHakmilik" value="${actionBean.hakmilikPermohonan.kodHakmilik.kod}" onchange="doSomething(this.value);" >
                                <s:option value="0">--Sila Pilih--</s:option>
                                <s:option value="GRN">Geran (Pendaftar)</s:option>
                                <s:option value="PN">Pajakan Negeri (Pendaftar)</s:option> 
                                <s:option value="GM">Geran Mukim (Pejabat Tanah)</s:option>
                                <s:option value="PM">Pajakan Mukim (Pejabat Tanah)</s:option>
                            </s:select>
                        </td>
                    </tr>
                <tr id="jikaPajakan">
                    <td>Tempoh Pajakan:</td>
                    <td>
                        <s:text name="hakmilikPermohonan.tempohPajakan" id="tempohPajakan" size="10"/>
                    </td>
                </tr>                              
                <tr>
                    <td>Premium (RM) : </td>
                    <td>
                        <s:text name="amnt" id="amnt" size="20" formatPattern="#,###,##0.00" value="${actionBean.hakmilikPermohonan.kadarPremium}"/>
                    </td>
                </tr>
                <tr>
                    <td>Hasil (RM) :</td>
                    <td>
                        <s:text name="hakmilikPermohonan.keteranganCukaiBaru" id="cukai" size="20" readonly="true"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Upah Ukur :
                    </td>
                    <td>
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUP" />&nbsp;Mengikut PU(A)438
                        <s:radio name="hakmilikPermohonan.agensiUpahUkur" value="JUB" />&nbsp;Juru Ukur Berlesen
                    </td>
                </tr>
            </table>
            <table class="tablecloth" align="center">
                <tr>
                    <td>
                        Syarat Nyata :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.syaratNyataBaru.syarat" id="syaratNyata" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kod" id="kod"/>                                    
                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('nyata')"/>  
                    </td>
                </tr>
                <tr>
                    <td>
                        Sekatan Kepentingan :
                    </td>
                    <td>
                        <s:textarea name="hakmilikPermohonan.sekatanKepentinganBaru.sekatan" id="syaratNyata1" rows="5" cols="80" readonly="yes" class="tooltips" value="${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}"></s:textarea>
                        <s:hidden name="disSyaratSekatan.kodSktn" id="kodSktn"/>

                    </td>
                    <td style="vertical-align: middle;">
                        <s:button name="cari" value="Cari" id="cari" class="btn" onclick="cariPopup('sekatan')"/>
                    </td>
                </tr>
            </table> 
        </c:when>
        <c:otherwise></c:otherwise>
    </c:choose>