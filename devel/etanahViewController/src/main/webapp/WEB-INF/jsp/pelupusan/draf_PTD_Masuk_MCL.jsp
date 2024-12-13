<%-- 
    Document   : draf_PTD_Masuk_MCL
    Created on : Aug 2, 2010, 2:11:29 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<s:form beanclass="etanah.view.stripes.pelupusan.DrafPertimbanganPTDMasukmclActionBean">
    <div class="subtitle">
        <s:messages/>
        <fieldset class="aras1">
            
            
            <legend><center><c:if test="${!data}"><s:textarea name="tajuk" rows="1" cols="130"></s:textarea></c:if>
                    <c:if test="${data}"><s:textarea name="tajuk" rows="2" cols="140"></s:textarea></c:if></center></legend>
                    <p align="center"><%--i. &nbsp; &nbsp; KEBENARAN UNTUK MENCATITKAN MCL--%>
                <br>            i. &nbsp; &nbsp; KEBENARAN UNTUK SERAH DAN MOHON SEMULA</p>
            <br><br>
            <b>1:</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>TUJUAN</b></u> <br>
            <p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <c:if test="${!data}"> <s:textarea name="tujuan" rows="2" cols="140"></s:textarea></c:if>
            <c:if test="${data}"><s:textarea name="tujuan" rows="2" cols="140"></s:textarea></c:if></p>
            <br><br>

            <b>2:</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>LATAR BELAKANG</b></u> <br><br>
            <p>&nbsp; &nbsp; &nbsp; &nbsp; 2.1.1 &nbsp; &nbsp; <c:if test="${!data}"><s:textarea name="latarBelakang1" rows="2" cols="140"></s:textarea></c:if>
                                                                <c:if test="${data}"><s:textarea name="latarBelakang1" rows="2" cols="140"></s:textarea></c:if><br><br>
        &nbsp; &nbsp; &nbsp; &nbsp; 2.1.2 &nbsp; &nbsp; <c:if test="${!data}"><s:textarea name="latarBelakang2" rows="2" cols="140"></s:textarea></c:if>
                                                            <c:if test="${data}"><s:textarea name="latarBelakang2" rows="2" cols="140"></s:textarea></c:if><br><br></p>

            <p>&nbsp; &nbsp; &nbsp; &nbsp; 2.2 &nbsp; &nbsp; Perihal Tanah<br><br>
            &nbsp; &nbsp; &nbsp; &nbsp; 2.2.1 &nbsp; &nbsp;</p><table border="0">
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Jenis dan No.Hakmilik </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.idHakmilik ne null}">${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}</c:if>
                        <c:if test="${actionBean.hakmilik.idHakmilik eq null}">Tiada &nbsp;</c:if>
                        </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>No. Lot/PT </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.noLot ne null}">Lot ${actionBean.hakmilik.noLot}</c:if>
                        <c:if test="${actionBean.hakmilik.noLot eq null}">Tiada &nbsp;</c:if>
                         </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Mukim/Bdr </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">${actionBean.hakmilik.bandarPekanMukim.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">Tiada &nbsp;</c:if>

                    </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Daerah </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.daerah.nama ne null}">${actionBean.hakmilik.daerah.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}">Tiada &nbsp;</c:if>
                        </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Hasil Tahunan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.cukai ne null}">RM ${actionBean.hakmilik.cukai}</c:if>
                          <c:if test="${actionBean.hakmilik.cukai eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Tempoh Pajakan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.tempohPegangan ne null}">${actionBean.hakmilik.tempohPegangan} Tahun</c:if>
                         <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Tarikh Tamat Pajakan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.tarikhLuput ne null}">${actionBean.hakmilik.tarikhLuput}</c:if>
                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Luas lot </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.luas ne null}">${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama}</c:if>
                       <c:if test="${actionBean.hakmilik.luas eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Penjenisan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                       <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td><b><u>Syarat Nyata</u></b> </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <br>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td><b><u>Sekatan Kepentingan</u></b> </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                       <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
            </table>
                <br><br>
                    <p>
                    &nbsp; &nbsp; &nbsp; &nbsp; 2.2.2 &nbsp; Tanah-tanah yang bersempadan dengan tanah ini ialah :-
                    <br><br></p>
                    <table border="0">
                        <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Utara </td>
                            <td>:</td>
                            <td>${actionBean}</td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Selatan </td>
                            <td>:</td>
                            <td>${actionBean}</td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Timur</td>
                            <td>:</td>
                            <td>${actionBean}</td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Barat </td>
                            <td>:</td>
                            <td>${actionBean}</td>
                        </tr>
                  </table>
                        <br><br>

                         3. &nbsp; &nbsp; &nbsp; &nbsp; <u><b>PERAKUAN PENOLONG PENTADBIR TANAH ${actionBean.daerah}</b></u> <br><br>
                        <p>&nbsp; &nbsp; &nbsp; &nbsp; 3.1 &nbsp; &nbsp; Pentadbiran ini telah meneliti permohonan ini dan memperakukan seperti berikut : <br>
                        <br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; a) Permohonan daripada ${actionBean.pemohon.pihak.nama} No.Kad Pengenalan: ${actionBean.pemohon.pihak.noPengenalan}, untuk menjadikan tanahnya Jenis dan No. Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}, Lot ${actionBean.hakmilik.noLot}, ${actionBean.hakmilik.bandarPekanMukim.nama} sebagai Tanah Adat Melaka (MCL) diluluskan mengikut 
                        <br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Seksyen 109(A) Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963.
                        <br><br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; b) Setelah catatan sebagai Tanah Adat Melaka (MCL) diselesaikan, Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} juga bersetuju mengeluarkan Hakmilik MCL Kekal dalam Daftar Hakmilik MCL Pejabat Tanah kepada pemilik tanah 
                        <br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; berkenaan, sekiranya pemiliknya membuat penyerahan dan memohon milik semula tanah tersebut.
                        <br><br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; c) Permohonan daripada ${actionBean.pemohon.pihak.nama} No.Kad Pengenalan: ${actionBean.pemohon.pihak.noPengenalan}, untuk mendapatkan hakmilik Tanah Adat Melaka (MCL) Kekal ke atas tanah No.Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik}, ${actionBean.hakmilik.bandarPekanMukim.nama} seluas ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama},
                        <br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Daerah ${actionBean.hakmilik.daerah.nama} diluluskan dengan dikenakan syarat-syarat seperti berikut :-
                        </p>
                        <br>
                        <table border="0">
                            <tr>
                            <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                            <td>Jenis Hakmilik</td>
                            <td>: </td>
                            <td>Tanah Adat Melaka (MCL) <s:hidden name="khm" value="MCL" id="MCL"/></td>
                            </tr>
                            <tr>
                            <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                            <td>Tempoh :</td>
                            <td>: </td>
                            <td>Kekal <s:hidden name="tmph" id="Kekal" value="Kekal"/></td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Premium</td>
                                <td>: </td>
                                <td>1/2(1/4(NP - (NP * BTP/100))</td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Cukai</td>
                                <td>: </td>
                                <td>RM ${actionBean.hakmilikPermohonan.cukaiBaru}</td>

                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Bayaran Hakmilik</td>
                                <td>:  </td>
                                <td> <table border="0">
                                        <tr>
                                            <td>a.</td>
                                            <td>Hakmilik Sementara</td>
                                            <td>: RM </td>
                                            <td>${actionBean.byrnhms}</td>
                                        </tr>
                                        <tr>
                                            <td>b.</td>
                                            <td>Hakmilik Kekal</td>
                                            <td>: RM</td>
                                            <td>${actionBean.byrnhmk}</td>
                                        </tr>

                                 </table></td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Syarat Nyata</td>
                                <td>: </td>
                                <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                                    <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">Tiada &nbsp;</c:if></td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Sekatan Kepentingan</td>
                                <td>: </td>
                                <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                                    <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada &nbsp;</c:if></td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Syarat Am</td>
                                <td>: </td>
                                <td>Tanah Adat Melaka</td>
                            </tr>
                            <tr>
                                <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
                                <td>Penjenisan</td>
                                <td>: </td>
                                <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                                    <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">Tiada &nbsp;</c:if></td>
                            </tr>
                        </table>
                        <br><br>
                        <p>4. &nbsp; &nbsp; &nbsp; &nbsp; <b>KEPUTUSAN</b> <br><br>
                        &nbsp; &nbsp; &nbsp; &nbsp; 4.1 &nbsp; &nbsp; Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} adalah diminta membuat keputusan samada bersetuju atau sebaliknya dengan perakuan yang dibuat seperti di perenggan 3.1 di atas. </p>

                     

                        <br><br>

                          <center><s:button name="simpanMohonKertas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/></center>
                        
        </fieldset>
    </div>


</s:form>


