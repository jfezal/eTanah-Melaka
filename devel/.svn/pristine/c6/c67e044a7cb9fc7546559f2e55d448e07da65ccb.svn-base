<%--
    Document   : draf_PTD_MCL
    Created on : Jul 14, 2010, 11:11:38 AM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass = "etanah.view.stripes.pelupusan.KeputusanPermohonanPertimbanganPTD_CatitMCLActionBean">
    <s:messages/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend align="center">
                <u><b><h5>KERTAS UNTUK PERTIMBANGAN PENTADBIR TANAH ${actionBean.daerah} BAGI PERMOHONAN UNTUK MENJADIKAN TANAH PAJAKAN SEBAGAI TANAH ADAT MELAKA (MCL)</h5></b></u>
            </legend>
            <br><br>
            <center><h6>Perwakilan Kuasa Oleh MMKN 21A/11/96</h6><br>
                    <h6>Yang Bersidang Pada 10 April, 1996 dan</h6><br>
                    <h6>Disahkan Pada 17 April, 1996</h6><br>
            </center>
            <br>
            TAJUK : &nbsp; &nbsp; 
            <center>
                <s:textarea name="tajuk" rows="2" cols="160" readonly="true"/></center>

            <br><br>

            <hr>

            <br><br>

            <b>1:</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>TUJUAN</b></u> <br>
            <p>&nbsp; &nbsp; &nbsp; 1.1 &nbsp; &nbsp;
            <s:textarea name="tujuan" rows="3" cols="160" readonly="true"/></p>
            
            <br><br>

            <b>2:</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>LATAR BELAKANG</b></u> <br><br>
            <p>&nbsp; &nbsp; &nbsp; 2.1 &nbsp; &nbsp; <u>Perihal Pemohon</u> <br>
            &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
            <s:textarea name="perihalPermohonan" rows="3" cols="160" readonly="true"/>
            <br><br>
            &nbsp; &nbsp; &nbsp; 2.2 &nbsp; &nbsp; <u><b>PERIHAL TANAH</b></u> <br><br>

            &nbsp; &nbsp; &nbsp; 2.2.1 </p><br>
            <table border="0">
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Jenis dan No.Hakmilik </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.noHakmilik ne null}">${actionBean.hakmilik.kodHakmilik.nama} dan ${actionBean.hakmilik.noHakmilik}</c:if>
                        <c:if test="${actionBean.hakmilik.noHakmilik eq null}">Tiada &nbsp;</c:if>
                        </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>No. Lot/PT </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.noLot ne null}">${actionBean.hakmilik.noLot}</c:if>
                         <c:if test="${actionBean.hakmilik.noLot eq null}">Tiada &nbsp;</c:if>
                         </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Mukim/Bdr </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.bandarPekanMukim.nama ne null}">${actionBean.hakmilik.bandarPekanMukim.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.bandarPekanMukim.nama eq null}">Tiada &nbsp;</c:if>

                    </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Daerah </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.daerah.nama ne null}">${actionBean.hakmilik.daerah.nama}</c:if>
                        <c:if test="${actionBean.hakmilik.daerah.nama eq null}">Tiada &nbsp;</c:if>
                        </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Hasil Tahunan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.cukai ne null}">${actionBean.hakmilik.cukai}</c:if>
                          <c:if test="${actionBean.hakmilik.cukai eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Tempoh Pajakan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.tempohPegangan ne null}">${actionBean.hakmilik.tempohPegangan}</c:if>
                         <c:if test="${actionBean.hakmilik.tempohPegangan eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Tarikh Tamat Pajakan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.tarikhLuput ne null}">${actionBean.hakmilik.tarikhLuput}</c:if>
                        <c:if test="${actionBean.hakmilik.tarikhLuput eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Luas lot </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.luas ne null}">${actionBean.hakmilik.luas}</c:if>
                       <c:if test="${actionBean.hakmilik.luas eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Bebanan </td>
                    <td>:</td>
                    <td>-</td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td>Penjenisan </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.kategoriTanah.nama ne null}">${actionBean.hakmilik.kategoriTanah.nama}</c:if>
                       <c:if test="${actionBean.hakmilik.kategoriTanah.nama eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td><b><u>Syarat Nyata</u></b> </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.syaratNyata.syarat ne null}">${actionBean.hakmilik.syaratNyata.syarat}</c:if>
                        <c:if test="${actionBean.hakmilik.syaratNyata.syarat eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
                <br>
                <tr>
                    <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                    <td><b><u>Syarat Kepentingan</u></b> </td>
                    <td>:</td>
                    <td><c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan ne null}">${actionBean.hakmilik.sekatanKepentingan.sekatan}</c:if>
                       <c:if test="${actionBean.hakmilik.sekatanKepentingan.sekatan eq null}">Tiada &nbsp;</c:if> </td>
                </tr>
            </table>
                <br><br>

                <p>&nbsp; &nbsp; &nbsp; 2.2.3 &nbsp; Tanah-tanah yang bersempadan dengan tanah ini ialah :-
                    <br><br></p>
                    <table border="0">
                        <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Utara </td>
                            <td>:</td>
                            <td>${actionBean.smpdnU} </td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Selatan </td>
                            <td>:</td>
                            <td>${actionBean.smpdnS} </td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Timur</td>
                            <td>:</td>
                            <td>${actionBean.smpdnT} </td>
                        </tr>
                         <tr>
                            <td>&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp</td>
                            <td>Barat </td>
                            <td>:</td>
                            <td>${actionBean.smpdnB} </td>
                        </tr>
                  </table>
                        <br><br>

         <b>3:</b> &nbsp; &nbsp; &nbsp; &nbsp; <u><b>PERAKUAN PENOLONG PENTADBIR TANAH {actionBean.daerah}</b></u> <br><br>
         <p>&nbsp; &nbsp; &nbsp; &nbsp; 3.1 &nbsp; &nbsp; Penolong Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} telah meniliti permohonan ini dan memperakukan supaya :- <br><br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; a) Permohonan daripada ${actionBean.pemohon.pihak.nama} untuk menjadikan tanahnya sebagai Tanah Adat Melaka (MCL) Jenis dan No.Hakmilik ${actionBean.hakmilik.kodHakmilik.kod} ${actionBean.hakmilik.noHakmilik} <b>Seluas</b> ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama}, <br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${actionBean.hakmilik.bandarPekanMukim.nama}, <b>Daerah ${actionBean.hakmilik.daerah.nama}, Melaka</b> dicatat (endorse) sebagai Tanah Adat Melaka (Malacca Customary Land) mengikut peruntukan Seksyen 109(A) Akta Kanun Tanah Negara <br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; (Hakmilik Pulau Pinang dan Melaka 1963). <br><br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; b) Setelah catatan sebagai Tanah Adat Melaka (Malacca Customary Land) diselesaikan, Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} memberikan persetujuan mengeluarkan hakmilik dalam daftar Hakmilik MCL <br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Pejabat Tanah kepada pemilik tanah tersebut, setelah pemilik berkenaan membuat penyerahan dan memohon milik semula tanah tersebut sebagai Tanah Adat Melaka (Malacca Customary Land)<br>
         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; hendaklah dikemukakan untuk pertimbangan Pentadbir ${actionBean.hakmilik.daerah.nama}. Bayaran premium dan syarat-syarat pemberian milik akan ditentukan kemudian.
         <%--<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; --%>
         <br><br>

         &nbsp; &nbsp; &nbsp; &nbsp; 3.2 &nbsp; &nbsp; &nbsp; Pentadbir Tanah ${actionBean.hakmilik.daerah.nama} adalah diminta <b>memutuskan samada bersetuju atau sebaliknya</b> dengan perakuan sebagaimana di perenggan 3.1 di atas.</p>
         
         

        <%-- <hr>

         <br><br>

         <center><h4><b>KEPUTUSAN PENTADBIR TANAH MELAKA TENGAH</b></h4>
             --------------------------------------------------------- <br>
             <h5>(RAHSIA)</h5>

         <br><br>

         <p>Saya Pentadbir Tanah Melaka Tengah melalui Perwakilan Kuasa MMKN 21A/11/96 yang bersidang pada 10 April 1996 dan disahkan pada 17 April 1996 <br>
  

           <s:radio name="kpsn" value="A" id="A" title="Setuju"/><b>Setuju</b> &nbsp;
           <s:radio name="kpsn" value="XA" id="XA" title="Tidak Setuju"/><b>Tidak Setuju</b>
           <br> 

            ke atas permohonan daripada ${actionBean.pemohon.pihak.nama} untuk menjadikan tanah milik sebagai Tanah Adat Melaka (MCL)ke atas <br>
            ${actionBean.hakmilik.noLot} , <b>Seluas</b> ${actionBean.hakmilik.luas} ${actionBean.hakmilik.kodUnitLuas.nama} ,${actionBean.hakmilik.bandarPekanMukim.nama}, Daerah Melaka Tengah, Melaka dengan syarat :-
            <br><br>
             a) Tanah ini dicatat (endorse) sebagai Tanah Adat Melaka (MCL) mengikut peruntukan Seksyen 109(A) Akta Kanun Tanah Negara (Hakmilik Pulau Pinang dan Melaka) 1963.

             <br><br>

              b) Setelah catatan sebagai Tanah Adat Melaka (MCL) diselesaikan, Pentadbir Tanah Melaka Tengah juga bersetuju mengeluarkan hakmilik dalam Daftar Hakmilik MCL Pejabat Tanah kepada pemilik tanah tersebut sekiranya pemilik
                 berkenaan membuat penyerahan dan memohon milik semula tanah tersebut.

                 <br><br>
         </p>--%>
               
<%--
                     <s:button name="simpanKeputusan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>--%>

               <%-- <c:if test="${actionBean.simpan}">

                    <b>Maklumat Telah Disimpan</b>
                    <br><br>
                     <s:button name="" id="next" value="Seterusnya" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>

                </c:if> --%>



         
        </fieldset>
    </div>

</s:form>