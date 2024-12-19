<%-- 
    Document   : kertas_siasaatn_N9
    Created on : Jan 11, 2012, 3:50:04 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

        <script type="text/javascript">
                function addRow(index,f)
                {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/kertas_siasatan?tambahRowMakluman&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function addRow1(index,f)
                {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/kertas_siasatan?tambahRow2&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function deleteRow(idKandungan,f)
                {
                    if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                         var q = $(f).formSerialize();
                        $.post('${pageContext.request.contextPath}/pelupusan/kertas_siasatan?deleteRowMakluman&idKandungan='+idKandungan,q,
                        function(data){
                            $('#page_div').html(data);
                        }, 'html');
                    }
                }
         </script>
    </head>
    <body>
       <s:form beanclass="etanah.view.stripes.pelupusan.KertasSiasatnN9ActionBean">
           <s:errors/>
           <s:messages/>
       <fieldset class="aras1">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'WMRE'}">
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK MERIZABKAN TANAH KERAJAAN ${actionBean.hakmilikPermohonan.hakmilik.noLot} SELUAS ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} DI ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}, DAERAH ${actionBean.permohonan.cawangan.daerah.nama} NEGERI SEMBILAN DARUL KHUSUS BAGI MAKSUD AWAM IAITU KAWASAN LAPANG DI BAWAH JAGAAN ${actionBean.pemohon.pihak.nama} SEBAGAI PEGAWAI PENGAWAL MENURUT SEKSYEN 3 ENAKMEN RIZAB MELAYU BAB 142 DAN PERKARA 89(3)(b) PERLEMBAGAAN PERSEKUTUAN.</span>
                            </p>
                        </td>
                    </tr>
                    </c:if>
                    
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'MMRE'}">
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK MEMASUKKAN TANAH MILIK YANG DIPEGANG DI BAWAH HAKMILIK ${actionBean.hakmilik.kodHakmilik} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}, DAERAH ${actionBean.permohonan.cawangan.daerah.nama} MENJADI KAWASAN SIMPANAN MELAYU MENGIKUT SEKSYEN 3 ENAKMEN RIZAB MELAYU BAB 142 DAN PERKARA 89(3)(b) PERLEMBAGAAN PERSEKUTUAN.</span>
                            </p>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'BMRE'}">
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK MERIZABKAN TANAH KERAJAAN ${actionBean.hakmilikPermohonan.hakmilik.noLot} SELUAS ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} DI ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}, DAERAH ${actionBean.permohonan.cawangan.daerah.nama} NEGERI SEMBILAN DARUL KHUSUS BAGI MAKSUD AWAM IAITU KAWASAN LAPANG DI BAWAH JAGAAN ${actionBean.pemohon.pihak.nama} SEBAGAI PEGAWAI PENGAWAL MENURUT SEKSYEN 3 ENAKMEN RIZAB MELAYU BAB 142 DAN PERKARA 89(3)(b) PERLEMBAGAAN PERSEKUTUAN.</span>
                            </p>
                        </td>
                    </tr>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK DISENARAIKAN KE DALAM JADUAL ${actionBean.hakmilikPermohonan.hakmilik.noLot} SELUAS ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama} DI ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}, DAERAH ${actionBean.permohonan.cawangan.daerah.nama} NEGERI SEMBILAN DARUL KHUSUS BAGI MAKSUD AWAM IAITU KAWASAN LAPANG DI BAWAH JAGAAN ${actionBean.pemohon.pihak.nama} SEBAGAI PEGAWAI PENGAWAL MENURUT SEKSYEN 3 ENAKMEN RIZAB MELAYU BAB 142 DAN PERKARA 89(3)(b) PERLEMBAGAAN PERSEKUTUAN.</span>
                            </p>
                        </td>
                    </tr>
                    </c:if>
                    
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'WMRE' && actionBean.permohonan.kodUrusan.kod ne 'MMRE' && actionBean.permohonan.kodUrusan.kod ne 'BMRE' && actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">LAPORAN DAN SIASATAN BAGI PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 63 KANUN TANAH NEGARA 1965. (PTS 528/152/09/06) PTG.NS 1/1/1/3178 </span>
                            </p>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">TUJUAN</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListOne1}" var="line" begin="0">
                    <s:hidden  name="1idMohonKertas${i}" id="1idMohonKertas${i}" value="${line.idKandungan}"/>
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${i}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${i}" class="normal_text" value="${line.kandungan}"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit}">
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${fn:length(actionBean.mohonKertasKandListOne1)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${fn:length(actionBean.mohonKertasKandListOne1)+1}" class="normal_text"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('1')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('1',this.form)"></s:button>
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('1',this.form)"></s:button></td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                            <td width="1%"> <b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">LATAR BELAKANG</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListTwo2}" var="line1" begin="0">
                    <s:hidden  name="2idMohonKertas${i}" id="2idMohonKertas${i}" value="${line1.idKandungan}"/>
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${i}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${i}" class="normal_text" value="${line1.kandungan}"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line1.idKandungan},this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit1}">
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${fn:length(actionBean.mohonKertasKandListTwo2)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${fn:length(actionBean.mohonKertasKandListTwo2)+1}" class="normal_text"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('2')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button>
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('2',this.form)"></s:button></td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                        <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">ULASAN Y.B. PENASIHAT UNDANG-UNDANG NEGERI</b></div></td>
                        </tr>

                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListFive5}" var="line2" begin="0">
                        <s:hidden  name="5idMohonKertas${i}" id="5idMohonKertas${i}" value="${line2.idKandungan}"/>
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="5kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line2.idKandungan},this.form)"></s:button></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                        <c:if test="${actionBean.edit2}">
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${fn:length(actionBean.mohonKertasKandListFive5)+1}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="5kandungan${fn:length(actionBean.mohonKertasKandListFive5)+1}" class="normal_text"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('5')"></s:button></td>
                        </tr>
                    </c:if>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button>
                                        <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('5',this.form)"></s:button></td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    </c:if>
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                        <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MAKLUMAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></div></td>
                        </tr>
                        
                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListThree3}" var="line2" begin="0">
                        <s:hidden  name="3idMohonKertas${i}" id="3idMohonKertas${i}" value="${line2.idKandungan}"/>
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${i}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line2.idKandungan},this.form)"></s:button></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                        <c:if test="${actionBean.edit2}">
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${fn:length(actionBean.mohonKertasKandListThree3)+1}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${fn:length(actionBean.mohonKertasKandListThree3)+1}" class="normal_text"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('3')"></s:button></td>
                        </tr>
                        </c:if>
                        <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('3',this.form)"></s:button></td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                        </c:if>
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">
                        <tr>
                                <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MAKLUMAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></div></td>
                        </tr>

                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListThree3}" var="line2" begin="0">
                        <s:hidden  name="3idMohonKertas${i}" id="3idMohonKertas${i}" value="${line2.idKandungan}"/>
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line2.idKandungan},this.form)"></s:button></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                        <c:if test="${actionBean.edit2}">
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${fn:length(actionBean.mohonKertasKandListThree3)+1}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${fn:length(actionBean.mohonKertasKandListThree3)+1}" class="normal_text"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('3')"></s:button></td>
                        </tr>
                        </c:if>
                        <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('3',this.form)"></s:button></td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>
                    </c:if>
                   
                    
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'JMRE'}">

                        <tr>
                                <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">KEPUTUSAN</b></div></td>
                        </tr>
                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListFour4}" var="line3" begin="0">
                        <s:hidden  name="4idMohonKertas${i}" id="4idMohonKertas${i}" value="${line3.idKandungan}"/>
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${i}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${i}" class="normal_text" value="${line3.kandungan}"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line3.idKandungan},this.form)"></s:button></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                        <c:if test="${actionBean.edit3}">
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${fn:length(actionBean.mohonKertasKandListFour4)+1}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${fn:length(actionBean.mohonKertasKandListFour4)+1}" class="normal_text"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('4')"></s:button></td>
                        </tr>
                        </c:if>
                        <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button>
                                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('4',this.form)"></s:button></td>
                        </tr>
                    </c:if>
                        
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'JMRE'}">
                        <tr>
                                <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">KEPUTUSAN</b></div></td>
                        </tr>
                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListFour4}" var="line3" begin="0">
                        <s:hidden  name="4idMohonKertas${i}" id="4idMohonKertas${i}" value="${line3.idKandungan}"/>
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.${i}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${i}" class="normal_text" value="${line3.kandungan}"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line3.idKandungan},this.form)"></s:button></td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        </c:forEach>
                        <c:if test="${actionBean.edit3}">
                        <tr>
                                <td>&nbsp;</td>
                                <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.${fn:length(actionBean.mohonKertasKandListFour4)+1}</b></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${fn:length(actionBean.mohonKertasKandListFour4)+1}" class="normal_text"/></td>
                                <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('4')"></s:button></td>
                        </tr>
                        </c:if>
                        <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button>
                                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('4',this.form)"></s:button></td>
                        </tr>
                    </c:if>
                </table>
      </fieldset>
       </s:form>
    </body>
</html>
