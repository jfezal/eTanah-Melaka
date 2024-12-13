<%-- 
    Document   : ulasan_rayuan_jktlm
    Created on : Jun 26, 2013, 12:42:39 AM
    Author     : muhammad.amin
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

<s:form beanclass="etanah.view.stripes.consent.UlasanRayuanJktlmActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Maklumat Kertas</legend>
            <table width="100%">
                <tr><td></td><td>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.tajuk eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.tajuk ne ' '}">
                                <s:textarea name="tajuk"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>

                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td width="2%">&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">Tajuk Kertas</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="tajuk" rows="4" style="width:97%;"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.tajuk eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.tajuk ne ' '}">                           
                                <s:textarea name="tajuk"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">1. TUJUAN PERMOHONAN</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="tujuan" rows="4" style="width:97%;" class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.tujuan eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.tujuan ne ' '}">
                                <s:textarea name="tujuan"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">2. LATAR BELAKANG</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="latarBelakang" rows="4" style="width:97%;" class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.latarBelakang eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.latarBelakang ne ' '}">
                                <s:textarea name="latarBelakang"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">3. PERIHAL TANAH</td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            3.1 Lokasi : <s:textarea name="lokasi" rows="4" style="width:97%;" class="normal_text"/><br/>
                            3.2 Keadaan Tanah : <s:textarea name="keadaanTanah" rows="4" style="width:97%;" class="normal_text"/><br/>
                            3.3 Perihal Tanah : <s:textarea name="perihalTanah" rows="4" style="width:97%;" class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.perihalTanah eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.perihalTanah ne ' '}"> 
                                <s:textarea name="perihalTanah"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PMJTL'}">
                            4. MAKSUD PINDAH MILIK
                        </c:if>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'PMJTL'}">
                            4. ALASAN RAYUAN
                        </c:if>
                    </td></tr>
                <tr><td></td><td>
                        <c:if test="${tajuk}">
                            <s:textarea name="maksud" rows="4" style="width:97%;" class="normal_text"/>
                        </c:if>
                        <c:if test="${viewTajuk}">
                            <c:if test="${actionBean.maksud eq ' '}">Tiada Data</c:if>
                            <c:if test="${actionBean.maksud ne ' '}"> 
                                <s:textarea name="maksud"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if>
                    </td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">5. BUTIR-BUTIR PEMOHON DAN PEMBELI</td></tr>

                <tr><td></td><td>
                        <table border="1">
                            <tr><td style="width:50px;" align="right">5.1</td><td>PEMOHON</td><td>&nbsp;</td></tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td align="right">
                                    Nama Pemohon :
                                </td>
                                <td>
                                    <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                                        <c:out value="${senarai.pihak.nama}"/>  (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)     
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr> 
                                <td>&nbsp;</td>
                                <td align="right">
                                    Alamat :
                                </td>
                                <td>
                                    <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">       
                                        <c:out value="${senarai.alamatSurat.alamatSurat1}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat2}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat3}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat4}"/>
                                        <c:out value="${senarai.alamatSurat.poskodSurat}"/>
                                        <c:out value="${senarai.alamatSurat.negeriSurat.nama}"/> &nbsp;
                                    </c:forEach>
                                </td>
                            </tr>

                            <c:set value="false" var="checkPgrhPemohon"/>
                            <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">  
                                <c:if test="${fn:length(senarai.pihak.senaraiPengarah) > 0}">
                                    <c:set value="true" var="checkPgrhPemohon"/>
                                </c:if>
                            </c:forEach>

                            <c:if test="${checkPgrhPemohon}">
                                <tr> 
                                    <td></td>
                                    <td align="right">
                                        Ahli Lembaga Pengarah :
                                    </td>
                                    <td>
                                        <c:forEach items="${actionBean.permohonan.senaraiPemohon}" var="senarai">  
                                            <c:if test="${fn:length(senarai.pihak.senaraiPengarah) > 0}">
                                                <c:forEach items="${senarai.pihak.senaraiPengarah}" var="senaraiPengarah"> 
                                                    <c:out value="${senaraiPengarah.nama}"/>&nbsp;- <c:out value="${senaraiPengarah.jenisPengenalan.nama}"/>&nbsp;<c:out value="${senaraiPengarah.noPengenalan}"/><br/> 
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                            <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                            <tr><td align="right">5.2</td><td>PEMBELI</td><td>&nbsp;</td></tr>
                            <tr>  
                                <td>&nbsp;</td>
                                <td align="right">
                                    Nama Pembeli :
                                </td>
                                <td>
                                    <c:forEach items="${actionBean.listPenerima}" var="senarai">  
                                        <c:out value="${senarai.pihak.nama}"/>  (<c:out value="${senarai.pihak.jenisPengenalan.nama}"/>  <c:out value="${senarai.pihak.noPengenalan}"/>)<br/>       
                                    </c:forEach>
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td align="right">Alamat :</td>
                                <td>
                                    <c:forEach items="${actionBean.listPenerima}" var="senarai"> 
                                        <c:out value="${senarai.alamatSurat.alamatSurat1}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat2}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat3}"/>
                                        <c:out value="${senarai.alamatSurat.alamatSurat4}"/>
                                        <c:out value="${senarai.alamatSurat.poskodSurat}"/>
                                        <c:out value="${senarai.alamatSurat.negeriSurat.nama}"/>&nbsp; <br/> 
                                    </c:forEach>
                                </td>
                            </tr>

                            <c:set value="false" var="checkPgrhPenerima"/>
                            <c:forEach items="${actionBean.listPenerima}" var="senarai">  
                                <c:if test="${fn:length(senarai.pihak.senaraiPengarah) > 0}">
                                    <c:set value="true" var="checkPgrhPenerima"/>
                                </c:if>
                            </c:forEach>

                            <c:if test="${checkPgrhPenerima}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td align="right">
                                        Ahli Lembaga Pengarah : 
                                    </td>
                                    <td>
                                        <c:forEach items="${actionBean.listPenerima}" var="senarai">  
                                            <c:if test="${fn:length(senarai.pihak.senaraiPengarah) > 0}">
                                                <c:forEach items="${senarai.pihak.senaraiPengarah}" var="senaraiPengarah"> 
                                                    <c:out value="${senaraiPengarah.nama}"/>&nbsp;- <c:out value="${senaraiPengarah.jenisPengenalan.nama}"/>&nbsp;<c:out value="${senaraiPengarah.noPengenalan}"/><br/>                                                        
                                                </c:forEach>  
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </td></tr>

                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">6. ULASAN JABATAN TEKNIKAL</td></tr>
                <tr><td></td><td>
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.senaraiRujukanLuar}" var="senarai">       
                            <c:if test="${senarai.agensi ne null}">
                                <c:out value="${senarai.agensi.nama}"/><br/>   
                                Nombor Rujukan : <s:text name="listUlasanTeknikal[${count - 1}].noRujukan" maxlength="50" size="50"/> 
                                Tarikh : <s:text name="listUlasanTeknikal[${count - 1}].tarikhRujukan"  id="datepicker${count}" class="datepicker" formatPattern="dd/MM/yyyy"/><br/>
                                <s:textarea name="listUlasanTeknikal[${count - 1}].ulasan" rows="4" style="width:97%;" class="normal_text"/><br/>&nbsp;<br/>
                            </c:if>
                            <c:set value="${count + 1}" var="count"/>
                        </c:forEach>&nbsp;
                    </td></tr>
                <tr><td>&nbsp;</td><td id="uLabel">7. PERAKUAN PENGARAH TANAH DAN GALIAN</td></tr>
                <tr><td></td><td>
                        <c:if test="${ptg}">
                            <s:textarea name="perakuanPtg" rows="4" style="width:97%;"  class="normal_text"/>
                        </c:if>
                        <c:if test="${viewPtg}">
                            <c:if test="${actionBean.perakuanPtg eq ' ' || actionBean.perakuanPtg eq null}">Tiada Data</c:if>
                            <c:if test="${actionBean.perakuanPtg ne ' '}">
                                <s:textarea name="perakuanPtg"  readonly="true" class="normal_text"
                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                            </c:if>
                        </c:if></td></tr>
                <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
                <%--                <tr><td>&nbsp;</td><td id="uLabel">8. KEPUTUSAN DIPOHON</td></tr>
                                <tr><td></td><td>
                                        <c:if test="${ptg}">
                                            <s:textarea name="keputusan" rows="4" style="width:97%;"  class="normal_text"/>
                                        </c:if>
                                        <c:if test="${viewPtg}">
                                            <c:if test="${actionBean.keputusan eq ' ' || actionBean.keputusan eq null}">Tiada Data</c:if>
                                            <c:if test="${actionBean.keputusan ne ' '}">
                                                <s:textarea name="keputusan"  readonly="true" class="normal_text"
                                                            style="width:97%;border:none;border-style:none;border-color: Transparent; overflow:visible; background: Transparent;"/>
                                            </c:if>
                                        </c:if></td></tr>--%>
                <!--<tr><td>&nbsp;</td><td>&nbsp;</td></tr>-->
            </table>
            <p align="center">
                <c:if test="${tajuk}">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </c:if>
            </p>
            <br/>
            <s:messages/>
            <s:errors/>
        </fieldset>
    </div>
</s:form>


