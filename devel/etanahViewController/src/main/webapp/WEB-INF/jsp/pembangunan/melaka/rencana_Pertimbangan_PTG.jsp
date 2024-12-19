<%--
    Document   : rencana_Pertimbangan_PTG
    Created on : July 07, 2010, 9:42:53 AM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<% pageContext.setAttribute("newline", "\n");%>
<style type="text/css">
    #tdLabel {
        color:#003194;
        display:block;
        float:left;
        font-family:Tahoma;
        font-size:13px;
        font-weight:bold;
        margin-left:30px;
        margin-right:0.5em;
        text-align:right;
        width:32em;
    }

    #tdDisplay {
        color:black;
        font-size:13px;
        font-weight:normal;
        float:left;
        width:20em;
    }
</style>

<script type="text/javascript">
    <%--function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 12){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var bil = document.createTextNode(rowcount);
           leftcell.appendChild(bil);

           var middlecell = row.insertCell(1);
           var sel = document.createElement('select');
           sel.name = 'subtajuk' + rowcount;
           sel.style.width = '400px';
           sel.options[0] = new Option('Sila Pilih','');
           sel.options[1] = new Option('Jabatan Kebajikan Masyarakat, Melaka','Jabatan Kebajikan Masyarakat, Melaka');
           sel.options[2] = new Option('Jabatan Kerja Raya, Melaka','Jabatan Kerja Raya, Melaka');
           sel.options[3] = new Option('Jabatan Pengairan dan Saliran, Melaka','Jabatan Pengairan dan Saliran, Melaka');
           sel.options[4] = new Option('Jabatan Perancang Bandar dan Desa, Melaka','Jabatan Perancang Bandar dan Desa, Melaka');
           sel.options[5] = new Option('Jabatan Tenaga Kerja, Melaka','Jabatan Tenaga Kerja, Melaka');
           sel.options[6] = new Option('Jabatan Perkhidmatan Kesihatan, Melaka','Jabatan Perkhidmatan Kesihatan, Melaka');
           sel.options[7] = new Option('Jabatan Pertanian, Melaka','Jabatan Pertanian, Melaka');
           sel.options[8] = new Option('Jabatan Alam Sekitar, Melaka','Jabatan Alam Sekitar, Melaka');
           sel.options[9] = new Option('Jabatan Perkhidmatan Pembentungan, Melaka','Jabatan Perkhidmatan Pembentungan, Melaka');
           sel.options[10] = new Option('Syarikat Air Melaka Berhad','Syarikat Air Melaka Berhad');
           sel.options[11] = new Option('Perbadanan Hang Tuah Jaya','Perbadanan Hang Tuah Jaya');
           sel.options[12] = new Option('Majlis Bandaraya Melaka Bersejarah','Majlis Bandaraya Melaka Bersejarah');
           sel.options[13] = new Option('Majlis Perbandaran Jasin','Majlis Perbandaran Jasin');
           sel.options[14] = new Option('Majlis Perbandaran Alor Gajah','Majlis Perbandaran Alor Gajah');
           middlecell.appendChild(sel);

           var rightcell = row.insertCell(2);
           var el = document.createElement('textarea');
           el.name = 'ulasan' + rowcount;
           el.rows = 3;
           el.cols = 90;
           el.style.textTransform = 'uppercase';
           rightcell.appendChild(el);
        } else{
            alert('Semua Jabatan Teknikal telah membuat ulasan.');
            $("#huraianptd").focus();
            return true;
        }

    }
    --%>
</script>
<s:form beanclass="etanah.view.stripes.pembangunan.RencanaPertimbanganPTGActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kertasK.kertas.idKertas"/>


    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <c:set scope="request" value="${actionBean.uniquePemohonList2}" var="senaraiPihak"/>
            <c:set scope="request" value="${actionBean.selectedPemohonPihak}" var="selectedPihak"/>
            <c:set scope="request" value="${actionBean.selectedPemohon}" var="selectedPemohon"/>
            <c:set scope="request" value="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik"/>
            <c:set scope="request" value="${actionBean.listPengarah}" var="senaraiPengarah"/>
            <div class="content" align="center">
                <table border="0" width="80%" cellspacing="10">
                    <tr><td align="center"><b>RENCANA UNTUK PERTIMBANGAN PENGARAH TANAH DAN GALIAN</b><br><br></td></tr>

                    <c:if test="${edit}">
                        <tr><td><b><s:textarea name="tajuk" rows="5" cols="160" class="normal_text"/></b></td></tr>
                    </c:if>

                    <c:if test="${!edit}">
                        <tr><td><b>${actionBean.tajuk}</b></td></tr>
                    </c:if>

                    <tr><td><b>1. PERIHAL PEMOHON</b></td></tr>
                    <tr><td>
                            <table>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Pemohon :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="100%">
                                            <c:if test="${selectedPemohon ne null}">
                                             <c:set value="1" var="i"/>
                                                <c:forEach items="${selectedPemohon}" var="pemohon" end="0">
                                                    <tr><td><font size="-1">                                                                
                                                            <c:out value="${pemohon.nama}"/> 

                                                            </font></td>
                                                    </tr>
                                                    <c:set value="${i + 1}" var="i"/>
                                                </c:forEach>  
                                            </c:if>
                                            <c:if test="${selectedPihak ne null}">
                                             <c:set value="1" var="i"/>
                                                <c:forEach items="${selectedPihak}" var="pemohon" end="0">
                                                    <tr><td><font size="-1">                                                                
                                                            <c:out value="${pemohon.nama}"/> 

                                                            </font></td>
                                                    </tr>
                                                    <c:set value="${i + 1}" var="i"/>
                                                </c:forEach>  
                                            </c:if>
                                                                                                                                      
                                        </table>
                                        <c:if test="${fn:length(senaraiPihak) > 1}">
                                              dan ${fn:length(senaraiPihak)-1} yang lain
                                        </c:if>  
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Alamat :</font></td>
                                    <td  id="tdDisplay">
                                        <table border="0" width="80%">
                                            <c:if test="${selectedPemohon ne null}">
                                                <c:set value="1" var="i"/>
                                                <c:forEach items="${selectedPemohon}" var="pemohon" end="0" >
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.alamat1}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                    <c:if test="${pemohon.alamat.alamat2 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat.alamat2}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${pemohon.alamat.alamat3 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat.alamat3}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${pemohon.alamat.alamat4 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat.alamat4}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>

                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat.poskod}"/></font>
                                                            <font size="-1"><c:out value="${pemohon.alamat.negeri.nama}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${i + 1}" var="i"/>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${selectedPihak ne null}">

                                                <c:set value="1" var="i"/>
                                                <c:forEach items="${selectedPihak}" var="pemohon" end="0" >
                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.alamat1}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                    <c:if test="${pemohon.alamat2 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat2}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${pemohon.alamat3 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat3}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:if test="${pemohon.alamat4 ne null}">
                                                        <tr>
                                                            <td>
                                                                <font size="-1"><c:out value="${pemohon.alamat4}"/>&nbsp;</font>
                                                            </td>
                                                        </tr>
                                                    </c:if>

                                                    <tr>
                                                        <td>
                                                            <font size="-1"><c:out value="${pemohon.poskod}"/></font>
                                                            <font size="-1"><c:out value="${pemohon.negeri.nama}"/>&nbsp;</font>
                                                        </td>
                                                    </tr>
                                                    <c:set value="${i + 1}" var="i"/>
                                                </c:forEach>
                                            </c:if>
                                               
                                            
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td  id="tdLabel"><font color="black">Tarikh Permohonan :</font></td>
                                    <td  id="tdDisplay">${actionBean.tarikhPermohonan}&nbsp;</td>
                                </tr>
                                <tr>
                                    <td id="tdLabel"><font color="black">Ahli Lembaga Pengarah :</font></td>
                                    <td  id="tdDisplay">
                                        <table>
                                            <c:set value="1" var="count"/>
                                            <c:forEach items="${senaraiPihak}" var="pihak" >
                                                <c:forEach items="${pihak.senaraiPengarah}" var="pengarah" >
                                                    <tr><td><font size="-1">
                                                            <%--  <c:out value="${count}"/>) &nbsp; --%>
                                                            <c:out value="${pengarah.nama}"/>
                                                            </font></td>
                                                    </tr>
                                                    <c:set value="${count + 1}" var="count"/>
                                                </c:forEach>
                                            </c:forEach>    
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>
                        <%--<tr>
                            <td>
                                <display:table class="tablecloth" name="${actionBean.listPemohon}" cellpadding="0" cellspacing="0" id="line">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line_rowNum}
                                        <s:hidden name="x" class="x${line_rowNum -1}" value="${line.pihak.idPihak}"/>
                                    </display:column>
                                    <display:column property="pihak.nama" title="Nama" style="vertical-align:baseline"/>
                                    <display:column title="Alamat" style="vertical-align:baseline">${line.pihak.suratAlamat1}
                                        <c:if test="${line.pihak.suratAlamat2 ne null}"> , ${line.pihak.suratAlamat2} </c:if>
                                        <c:if test="${line.pihak.suratAlamat3 ne null}"> , ${line.pihak.suratAlamat3} </c:if>
                                        <c:if test="${line.pihak.suratAlamat4 ne null}"> , ${line.pihak.suratAlamat4} </c:if>
                                        ${line.pihak.suratPoskod}
                                        ${line.pihak.suratNegeri.nama}
                                    </display:column>
                                </display:table>
                            </td>
                        </tr> --%>

                    <tr><td><b>2. TUJUAN</b></td></tr>
                    <%--<c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                        <c:if test="${edit}">
                            <tr><td>${actionBean.tujuan}</td></tr>
                        </c:if>
                    </c:if>--%>
                    <%--<c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">--%>
                    <c:if test="${edit}">
                        <tr><td><b><s:textarea name="tujuan" rows="5" cols="160" class="normal_text"/></b></td></tr>
                    </c:if>
                    <%--</c:if>--%>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.tujuan}</td></tr>
                    </c:if>

                    <tr><td><b>3. LOKASI TANAH DAN KEADAAN TANAH</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="lokasiTanah" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.lokasiTanah} &nbsp;</td></tr>
                    </c:if>                                        

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSPSS'}">
                        <tr><td><b></b></td></tr>
                        <tr>
                            <td>
                                <table class="tablecloth">
                                    <tr>
                                        <th>&nbsp;</th><th>Kategori Tanah</th><th>Nombor Lot</th><th>Kegunaan</th>
                                    </tr>
                                    <tr>
                                        <th>
                                            Utara
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanUtaraNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanUtaraKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanUtaraKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Selatan
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot ne null}"> ${actionBean.laporanTanah.sempadanSelatanNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan ne null}"> ${actionBean.laporanTanah.sempadanSelatanKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanSelatanKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Timur
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Barat
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Timur Laut
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurLautMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot ne null}"> ${actionBean.laporanTanah.sempadanTimurLautNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurLautNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTimurlautKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTimurlautKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Tenggara
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot ne null}"> ${actionBean.laporanTanah.sempadanTenggaraNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan ne null}"> ${actionBean.laporanTanah.sempadanTenggaraKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanTenggaraKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Barat Laut
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratLautMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratlautNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratlautNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratlautKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratlautKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th>
                                            Barat Daya
                                        </th>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan ne 'T'}">Kerajaan</c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaMilikKerajaan eq 'T'}">Milik</c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaNoLot}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaNoLot eq null}">  </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan ne null}"> ${actionBean.laporanTanah.sempadanBaratdayaKegunaan}&nbsp; </c:if>
                                            <c:if test="${actionBean.laporanTanah.sempadanBaratdayaKegunaan eq null}">  </c:if>
                                        </td>
                                    </tr>
                                </table>
                            </td></tr>
                        </c:if>
                    <tr><td><b>Maklumat Lain</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><s:textarea rows="5" cols="150" name="maklumatLain" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>${actionBean.maklumatLain} &nbsp;</td></tr>
                    </c:if>   

                    <tr><td><b>4.  BUTIR-BUTIR HAKMILIK </b></td></tr>
                    <tr><td>
                            <display:table class="tablecloth" name="${actionBean.hakmilikPermohonanList}" cellpadding="0" cellspacing="0" id="tbl1">
                                <display:column title="Bil" sortable="true" style="vertical-align:baseline">${tbl1_rowNum}</display:column>
                                <display:column title="No.Lot" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} <fmt:formatNumber pattern="00" value="${tbl1.hakmilik.noLot}"/>                                     
                                </display:column>
                                <display:column title="Jenis & No Hakmilik" style="vertical-align:baseline">
                                    ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.kodHakmilik.kod} <fmt:formatNumber pattern="00" value="${tbl1.hakmilik.noHakmilik}"/>                         
                                </display:column>
                                <display:column title="Luas" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${tbl1.hakmilik.luas}"/>&nbsp;${tbl1.hakmilik.kodUnitLuas.nama}</display:column>
                                <display:column title="Bandar/Pekan/Mukim" property="hakmilik.bandarPekanMukim.nama"  style="vertical-align:baseline"/>
                                <display:column title="Kategori Tanah" property="hakmilik.kategoriTanah.nama" style="vertical-align:baseline"/>
                                <display:column title="Syarat Nyata" property="hakmilik.syaratNyata.syarat" style="vertical-align:baseline"/>
                                <display:column title="Sekatan Kepentingan" property="hakmilik.sekatanKepentingan.sekatan" style="vertical-align:baseline"/>
                                <display:column title="Bebanan Berdaftar" style="vertical-align:baseline"></display:column>
                                <display:column title="Kedudukan Tanah" style="vertical-align:baseline">
                                    <c:forEach items="${tbl1.senaraiLaporanTanah}" var="tbl2">
                                        ${tbl2.kedudukanTanah}<br>
                                    </c:forEach>
                                </display:column>
                            </display:table>
                        </td></tr>


                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                        <tr><td><b>4. BENTUK PEMBANGUNAN YANG DICADANGKAN</b></td></tr>
                        <tr><td>Pemohon bercadang untuk membangunkan tanah tersebut seperti berikut: <td></tr>
                        <tr><td align="center">
                                <display:table cellpadding="0" cellspacing="0" class="tablecloth" name="${actionBean.listplotpelan}" id="line1">
                                    <display:column title="Bil" sortable="true" style="vertical-align:baseline">${line1_rowNum}</display:column>
                                    <display:column title="Butir-butir Pembangunan" style="vertical-align:baseline">${line1.kegunaanTanah.nama}</display:column>
                                    <display:column title="Bil Unit" style="vertical-align:baseline">${line1.bilanganPlot}</display:column>
                                    <display:column title="Keluasan Lot" style="vertical-align:baseline"><fmt:formatNumber pattern="#,##0.0000" value="${line1.luas}"/>&nbsp;${line1.kodUnitLuas.nama}</display:column>
                                </display:table>
                            </td>
                        </tr>
                    </c:if>

                    <tr><td><b>5. ULASAN JABATAN - JABATAN TEKNIKAL </b></td></tr>
                    <tr><td>
                            <c:if test="${edit}">
                                <display:table name="${actionBean.ulasanList}" id="line1" class="tablecloth" size="100">
                                    <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                    <display:column title="Jabatan" style="width:400px" property="agensi.nama"></display:column>
                                    <c:if test = "${line1.noRujukan ne null}">
                                        <display:column title="Rujukan Surat" style="width:400px">                                     
                                            ${line1.noRujukan} Bertarikh <fmt:formatDate value="${line1.tarikhTerima}" pattern="dd/MM/yyyy"/>
                                        </display:column>
                                    </c:if>
                                    <c:if test = "${line1.noRujukan eq null}">
                                        <display:column title="Rujukan Surat" style="width:400px">                                     

                                        </display:column>
                                    </c:if>
                                    <display:column title="Ulasan" style="width:450px"><%--${fn:replace(line1.ulasan,newline,"<br/>")}--%>
                                        <s:textarea name="ulasanJabatan${line1_rowNum}" id="ulasanJabatan${line1_rowNum}"  rows="4" cols="50" class="normal_text">${fn:replace(line1.ulasan,newline,"")}</s:textarea>
                                    </display:column>
                                </display:table>
                            </c:if>

                            <c:if test="${!edit}">
                                <display:table name="${actionBean.ulasanList}" id="line1" class="tablecloth" size="100">
                                    <display:column title="Bil" sortable="true">${line1_rowNum}</display:column>
                                    <display:column title="Jabatan" style="width:400px" property="agensi.nama"></display:column>
                                    <c:if test = "${line1.noRujukan ne null}">
                                        <display:column title="Rujukan Surat" style="width:400px">                                     
                                            ${line1.noRujukan} Bertarikh <fmt:formatDate value="${line1.tarikhTerima}" pattern="dd/MM/yyyy"/>
                                        </display:column>
                                    </c:if>
                                    <c:if test = "${line1.noRujukan eq null}">
                                        <display:column title="Rujukan Surat" style="width:400px">                                     

                                        </display:column>
                                    </c:if>
                                    <display:column title="Ulasan" style="width:450px"><%--${fn:replace(line1.ulasan,newline,"<br/>")}--%>                                
                                        ${fn:replace(line1.ulasan,newline,"")}
                                    </display:column>
                                </display:table>
                            </c:if>
                        </td></tr>
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                        <tr><td><b>6. ULASAN YANG BERHORMAT ADUN KAWASAN</b></td></tr>
                        <tr><td>                            
                                <c:if test="${actionBean.ulasanAdun eq null}">
                                    TIADA DATA.
                                </c:if>
                                <c:if test="${actionBean.ulasanAdun ne null}">
                                    <s:textarea rows="5" cols="160" name="ulasan" class="normal_text" />
                                    <%--${fn:replace(actionBean.ulasanAdun.ulasan,newline,"<br/>")}--%>
                                </c:if>

                            </td></tr>
                        </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSPSS'}">
                        <tr><td><b>6. CUKAI DAN PERSETUJUAN PIHAK YANG BERKEPENTINGAN</b></td></tr>
                        <tr><td>                            
                                <c:if test="${edit}">   
                            <tr><td><b>Cukai Tanah Telah Dijelaskan Pada : <s:text name="tarikhCukai" id="datepicker" class="datepicker" size="12"/></b></td></tr>  
                            <tr><td><b>No. Resit :</b></td></tr>
                            <tr><td><s:textarea rows="5" cols="150" name="noResit" class="normal_text" /></td></tr>
                            <tr><td><b>Pihak Yang Berkepentingan : </b></td></tr>
                            <tr><td><s:textarea rows="5" cols="150" name="pihakBerkepentingan" class="normal_text" /></td></tr>
                        </c:if>

                        <c:if test="${!edit}">
                            <tr><td><b>Cukai Tanah Telah Dijelaskan Pada : ${actionBean.tarikhCukai}</b></td></tr>  
                            <tr><td><b>No. Resit : ${actionBean.noResit}</b></td></tr>
                            <tr><td><b>Pihak Yang Berkepentingan : ${actionBean.pihakBerkepentingan}</b></td></tr>
                        </c:if>
                        </td></tr>
                    </c:if>

                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSPSS'}">
                        <tr><td><b><font style="text-transform:uppercase">7. PERAKUAN PENTADBIR TANAH ${actionBean.pejTanah}</font></b></td></tr>
                                    <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                                        <c:if test="${edit}">
                                <tr><td> ${actionBean.ulasanPentadbir}</td></tr>
                            </c:if>
                        </c:if>
                        <c:if test="${actionBean.pengguna.kodCawangan.kod ne '00'}">
                            <c:if test="${edit}">
                                <tr><td><s:textarea rows="15" cols="150" name="ulasanPentadbir" class="normal_text" /></td></tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td> ${actionBean.ulasanPentadbir}</td></tr>
                        </c:if>
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'TSPSS'}">
                        <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                            <tr><td><b>8. PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>
                            <c:if test="${edit}">
                                <tr><td><s:textarea name="perakuan" rows="5" cols="160" class="normal_text" /></td></tr>
                            </c:if>
                            <c:if test="${!edit}">
                                <tr><td>${actionBean.perakuan}&nbsp;</td></tr>
                            </c:if>
                        </c:if>  
                    </c:if>
                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'TSPSS'}">
                        <tr><td><b>7. ULASAN PENTADBIR TANAH</b></td></tr>
                        <tr><td><s:textarea rows="5" cols="160" name="ulasanPentadbir" class="normal_text" /></td></tr>
                        <br>
                        <tr><td><b>8. KEPUTUSAN MESYUARAT JAWATANKUASA BELAH BAHAGI NEGERI MELAKA</b></td></tr>
                        <tr><td><s:textarea rows="5" cols="160" name="keputusanjkbb" class="normal_text"/></td></tr>
                        <br>
                        <tr><td><b>9. SYOR</b></td></tr>
                        <tr><td><s:textarea name="syor" rows="5" cols="160" class="normal_text"/></td></tr>
                        <br>
                        <c:if test="${actionBean.stageId ne 'sediarencanaringkasptg'}">
                            <c:if test="${actionBean.pengguna.perananUtama.kod eq '12' || actionBean.pengguna.perananUtama.kod eq '233'}">
                                <tr><td><b>10. KEPUTUSAN YANG DIPOHON</b></td></tr>
                                <tr><td>Saya <s:radio name="kpsnPohon" value="bersetuju"/>&nbsp; <b>BERSETUJU</b> &nbsp;
                                        <s:radio name="kpsnPohon" value="tidak bersetuju"/>&nbsp; <b>TIDAK BERSETUJU</b> &nbsp;
                                        * syor seperti di para 9 di atas.</td></tr>
                                    </c:if>
                                    <%--<c:if test="${actionBean.pengguna.perananUtama.kod ne '12'}">
                                        <tr><td><b>10. KEPUTUSAN YANG DIPOHON</b></td></tr>
                                        <tr><td>Saya <s:radio name="kpsnPohon" value="bersetuju" disabled="true"/>&nbsp; <b>BERSETUJU</b> &nbsp; &nbsp;
                                                <s:radio name="kpsnPohon" value="tidak bersetuju" disabled="true"/>&nbsp; <b>TIDAK BERSETUJU</b> &nbsp; &nbsp;
                                                * syor seperti di para 9 di atas.</td></tr>
                                    </c:if>--%>
                                </c:if>
                            </c:if>
                </table>
                <c:if test="${edit}">
                    <br>
                    <p align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </p>
                </c:if>
            </div>
        </fieldset>
    </div>
</s:form>