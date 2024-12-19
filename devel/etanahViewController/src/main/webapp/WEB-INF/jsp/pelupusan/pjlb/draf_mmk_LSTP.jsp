<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">


<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">
    
    function menyimpan(index,i,f){
        var kand;
        if(index==3){
            kand = document.getElementById("kandungan3"+i).value;
        }
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==9){
            kand = document.getElementById("kandungan9"+i).value;
        }
        if(index==10){
            kand = document.getElementById("kandungan10"+i).value;
        }
        if(index==11){
            kand = document.getElementById("kandungan11"+i).value;
        }
        if(index==12){
            kand = document.getElementById("kandungan12"+i).value;
        }
        var viewMMKN = $('#viewMMKN').val();
        var q = $(f).formSerialize();
        $.post('${pageContext.request.contextPath}/pelupusan/draf_MMKLSTP?simpanKandungan&index='+index+'&kandungan='+kand+'&viewMMKN='+viewMMKN,q,
        function(data){
            $('#page_div').html(data);
        }, 'html');
    }
    function deleteRow(idKandungan,f){
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var viewMMKN = $('#viewMMKN').val();
            var q = $(f).formSerialize();
            $.post('${pageContext.request.contextPath}/pelupusan/draf_MMKLSTP?deleteRow&idKandungan='+idKandungan+'&viewMMKN='+viewMMKN,q,
            function(data){
                $('#page_div').html(data);
            }, 'html');
        }}
    
      
    function addRow(index){
        var viewMMKN = $('#viewMMKN').val();
        var url = '${pageContext.request.contextPath}/pelupusan/draf_MMKLSTP?tambahRow&index='+index+'&viewMMKN='+viewMMKN;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
        
    }
</script>
<s:useActionBean beanclass="etanah.view.ListUtil" var="list"/>
<s:form beanclass="etanah.view.stripes.pelupusan.DrafMMK_LSTPActionBean" name="form" id="form">
    <s:hidden id="viewMMKN" name="viewMMKN" value="${actionBean.viewForMMKN}"/>
    <s:messages/>
    <s:errors/>
    <table width="80%" border="0">
        <tr>
            <td align="left"><b>RAHSIA</b></td>
        </tr>
    </table>
    <table width="80%" border="0">
        <!--TajukUtama-->
        <tr>
            <td colspan="4">
                <div class="subtitle" style="text-transform: capitalize">
                    <fieldset class="aras1">
                        <c:if test="${actionBean.kodNeg eq '04'}">
                            <legend> <c:if test="${actionBean.urusanStatus eq 'PJLB'}">
                                    MESYUARAT JAWATANKUASA SUMBER MINERAL NEGERI MELAKA
                                </c:if></legend>
                            </c:if>
                            <c:if test="${actionBean.kodNeg eq '05'}">
                            <legend> <c:if test="${actionBean.urusanStatus eq 'PJLB' or actionBean.urusanStatus eq 'MPJLB' or actionBean.urusanStatus eq 'LSTP'}">
                                    MESYUARAT JAWATANKUASA SUMBER MINERAL NEGERI SEMBILAN
                                </c:if></legend>
                            </c:if>
                        <div class="content" align="center">

                            <table border="0" width="70%" cellspacing="10%" align="center">
                                <tr><td id="tdLabel"><b><font style="text-transform: capitalize">
                                                <tr><td>
                                                        <p align="center"><b><span style="text-transform:uppercase">${actionBean.tajukUtama}</span></b></p>

                                                    </td></tr></font></b></td></tr>

                                <tr><td>&nbsp;</td></tr>
                            </table>
                        </div>
                    </fieldset>
                </div>
            </td>
        </tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <br>

        <!--TujuanPermohonan-->
        <tr>
            <td><b>1.</b></td>
            <td colspan="3"><div align="left"><b>TUJUAN PERMOHONAN</b></div></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td>
                ${actionBean.tujuanPermohonan}
                <!--
                <s:textarea name="tujuanPermohonan" class="normal_text" rows="5" cols="150" readonly="true"/>
                -->
            </td>
        </tr>
        <br>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <tr>
            <td><b>2.</b></td>
            <td colspan="3"><div align="left"><b>LATAR BELAKANG</b></div></td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:if test="${!actionBean.viewForMMKN}">
            <c:set var="i" value="1" />
            <c:set var="k" value="1" />
            <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalPermohonan}" var="line">

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="2.${k}"/></td>
                    <td><s:textarea  id="kandungan3${i}"name="senaraiLaporanKandunganPerihalPermohonan[${i-1}].kandungan" cols="80"  rows="5" class="normal_text"/>
                        <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                    </td>

                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="k" value="${k+1}" />
            </c:forEach>

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td  align="left">
                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3')"></s:button>
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1})"></s:button>
                </td>
            </tr>
        </c:if>
        <c:if test="${actionBean.viewForMMKN}">
            <c:set var="i" value="1" />
            <c:set var="k" value="1" />
            <c:forEach items="${actionBean.senaraiLaporanKandunganPerihalPermohonan}" var="line">

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td style="display:none" valign="top">${line.idKandungan}</td><td valign="top"><c:out value="3.1.${k}"/></td>
                    <td colspan="2">${line.kandungan}</td>
                </tr>
                <c:set var="i" value="${i+1}" />
                <c:set var="k" value="${k+1}" />
            </c:forEach>
        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--Perakuan Jawatankuasa Sumber Mineral-->
        <c:if test="${!actionBean.viewMMKN}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG'}">
                <tr>
                    <td><b>3.</b></td>
                    <td colspan="3">
                        <div align="left">
                            <b>PERAKUAN JAWATANKUASA SUMBER MINERAL NEGERI, NEGERI SEMBILAN
                            </b>
                        </div>
                    </td>
                </tr>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG'}">
                    <c:set var="i" value="1" />
                    <c:set var="k" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td style="display:none" valign="top">${line.idKandungan}</td>
                            <td valign="top">
                                <c:if test="${!actionBean.viewMMKN}">
                                    <c:out value="3.${k}"/>
                                </c:if>
                                <c:if test="${actionBean.viewMMKN}">
                                    <c:out value="3.${k}"/>
                                </c:if>
                            </td>

                            <td><s:textarea  id="kandungan9${i}"name="senaraiLaporanKandunganPerakuanPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                            </td>

                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="k" value="${k+1}" />
                    </c:forEach>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td  align="left">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('9',${i-1})"></s:button>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                    <c:set var="i" value="1" />
                    <c:set var="k" value="1" />
                    <c:forEach items="${actionBean.senaraiMohonRujukLuar}" var="line">

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td style="display:none" valign="top">&nbsp;</td>
                            <td valign="top">
                                <c:if test="${!actionBean.viewMMKN}">
                                    <c:out value="9.${k}"/>
                                </c:if>
                                <c:if test="${actionBean.viewMMKN}">
                                    <c:out value="10.${k}"/>
                                </c:if>
                            </td>

                            <td>${line.ulasan}</td>
                            <td>&nbsp;</td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="k" value="${k+1}" />
                    </c:forEach>
                </c:if>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>

        </c:if>
        <c:if test="${actionBean.viewMMKN}">
            <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG'}">
                <tr>
                    <td><b>3.</b></td>
                    <td colspan="3"><div align="left"><b>PERAKUAN JAWATANKUASA SUMBER MINERAL NEGERI, NEGERI SEMBILAN </b></div></td>
                </tr>
                <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'LMCRG' and actionBean.permohonan.kodUrusan.kod ne 'PJLB' and actionBean.permohonan.kodUrusan.kod ne 'MPJLB' and actionBean.permohonan.kodUrusan.kod ne 'PCRG'}">
                    <c:set var="i" value="1" />
                    <c:set var="k" value="1" />
                    <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTD}" var="line">

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td style="display:none" valign="top">${line.idKandungan}</td>
                            <td valign="top">
                                <c:if test="${!actionBean.viewMMKN}">
                                    <c:out value="3.${k}"/>
                                </c:if>
                                <c:if test="${actionBean.viewMMKN}">
                                    <c:out value="3.${k}"/>
                                </c:if>
                            </td>

                            <td><s:textarea  id="kandungan9${i}"name="senaraiLaporanKandunganPerakuanPTD[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                                <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                            </td>

                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="k" value="${k+1}" />
                    </c:forEach>

                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                        <td  align="left">
                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('9')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('9',${i-1})"></s:button>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LMCRG'}">
                    <c:set var="i" value="1" />
                    <c:set var="k" value="1" />
                    <c:forEach items="${actionBean.senaraiMohonRujukLuar}" var="line">

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td style="display:none" valign="top">&nbsp;</td>
                            <td valign="top">
                                <c:if test="${!actionBean.viewMMKN}">
                                    <c:out value="9.${k}"/>
                                </c:if>
                                <c:if test="${actionBean.viewMMKN}">
                                    <c:out value="10.${k}"/>
                                </c:if>
                            </td>

                            <td>${line.ulasan}</td>
                            <td>&nbsp;</td>
                        </tr>
                        <c:set var="i" value="${i+1}" />
                        <c:set var="k" value="${k+1}" />
                    </c:forEach>
                </c:if>
                <tr>
                    <td colspan="4">&nbsp;</td>
                </tr>
            </c:if>

        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <tr>
            <td><b>4.</b></td>
            <td colspan="3"><div align="left"><b>ULASAN JABATAN-JABATAN TEKNIKAL</b></div></td>
        </tr>

        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:forEach items="${actionBean.senaraiUlasanJabatanTeknikal}" var="line">
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td valign="top"><c:out value="4.${i}"/></td>
                <td colspan="2"><font style="font-weight:bold;">${line.namaAgensi}</font></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td valign="top">&nbsp;</td>
                <td colspan="2">( Ruj. Surat ${line.noRujukan} bertarikh <s:format value="${line.tarikhRujukan}" formatPattern="dd/MM/yyyy"/> )</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td>
                    ${line.ulasan}
                </td>
            </tr>
            <c:set var="i" value="${i+1}" />
        </c:forEach>
        <%--</c:if>--%>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--Keputusan JKM-->
        <c:if test="${actionBean.viewMMKN}">
            <tr>  
                <td><b>9.</b></td>
                <td colspan="3"><div align="left"><b>KEPUTUSAN JAWATANKUASA SUMBER MINERAL</b></div></td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>

            <tr>  
                <td>&nbsp;</td>
                <td colspan="3">
                    <table border="0">
                        <tr>
                            <td><div align="right">Syor : </div></td>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'L'}">
                                        Diluluskan
                                    </c:when>
                                    <c:when test="${actionBean.mohonRujLuarJKM.diSokong eq 'T'}">
                                        Ditolak
                                    </c:when>
                                </c:choose>                            
                            </td>
                        </tr>
                        <tr>
                            <td><div align="right">Ulasan : </div></td>
                            <td colspan="3">${actionBean.mohonRujLuarJKM.ulasan}</td>
                        </tr>
                        <c:set var="i" value="1" />
                        <c:set var="k" value="1" />
                        <c:forEach items="${actionBean.senaraiLaporanUlas}" var="line">

                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${i eq 1}">
                                            Terma Dan Syarat :
                                        </c:when>
                                        <c:otherwise>
                                            &nbsp;
                                        </c:otherwise>                                                
                                    </c:choose>
                                </td>
                                <td>&nbsp;</td>
                                <td colspan="2">${i}) ${line.ulasan}</td>

                            </tr>
                            <c:set var="i" value="${i+1}" />
                            <c:set var="k" value="${k+1}" />
                        </c:forEach>
                    </table>

                </td>                
            </tr>

            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
        </c:if>

        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <!--Perakuan PTG-->
        <c:if test="${!actionBean.viewMMKN}">
            <tr>
                <td><b>5.</b></td>
                <td colspan="3"><div align="left"><b>HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS </b></div></td>
            </tr>
        </c:if>
        <c:if test="${actionBean.viewMMKN}">

            <tr>
                <td><b>5.</b></td>
                <td colspan="3"><div align="left"><b>HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS </b></div></td>
            </tr>

        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:set var="k" value="1" />
        <c:forEach items="${actionBean.senaraiLaporanKandunganPerakuanPTG}" var="line">

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td style="display:none" valign="top">${line.idKandungan}</td>
                <td valign="top">
                    <c:if test="${!actionBean.viewMMKN}">
                        <c:out value="5.${k}"/>
                    </c:if>
                    <c:if test="${actionBean.viewMMKN}">
                        <c:out value="5.${k}"/>
                    </c:if>
                </td>
                <td><s:textarea  id="kandungan10${i}"name="senaraiLaporanKandunganPerakuanPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                </td>

            </tr>
            <c:set var="i" value="${i+1}" />
            <c:set var="k" value="${k+1}" />
        </c:forEach>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td  align="left">
                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('10')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('10',${i-1})"></s:button>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <!--SYOR PTG-->
        <c:if test="${!actionBean.viewMMKN}">
            <tr>
                <td><b>6.</b></td>
                <td colspan="3"><div align="left"><b>SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS </b></div></td>
            </tr>
        </c:if>
        <c:if test="${actionBean.viewMMKN}">

            <tr>
                <td><b>6.</b></td>
                <td colspan="3"><div align="left"><b>SYOR PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN DARUL KHUSUS </b></div></td>
            </tr>

        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:set var="k" value="1" />
        <c:forEach items="${actionBean.senaraiSyorPTG}" var="line">

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td style="display:none" valign="top">${line.idKandungan}</td>
                <td valign="top">
                    <c:if test="${!actionBean.viewMMKN}">
                        <c:out value="6.${k}"/>
                    </c:if>
                    <c:if test="${actionBean.viewMMKN}">
                        <c:out value="6.${k}"/>
                    </c:if>
                </td>
                <td><s:textarea  id="kandungan11${i}"name="senaraiSyorPTG[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                </td>

            </tr>
            <c:set var="i" value="${i+1}" />
            <c:set var="k" value="${k+1}" />
        </c:forEach>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td  align="left">
                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('11')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('11',${i-1})"></s:button>
            </td>
        </tr>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>

        <!--PENUTUP-->
        <c:if test="${!actionBean.viewMMKN}">
            <tr>
                <td><b>7.</b></td>
                <td colspan="3"><div align="left"><b>PENUTUP </b></div></td>
            </tr>
        </c:if>
        <c:if test="${actionBean.viewMMKN}">

            <tr>
                <td><b>7.</b></td>
                <td colspan="3"><div align="left"><b>PENUTUP </b></div></td>
            </tr>

        </c:if>
        <tr>
            <td colspan="4">&nbsp;</td>
        </tr>
        <c:set var="i" value="1" />
        <c:set var="k" value="1" />
        <c:forEach items="${actionBean.penutup}" var="line">

            <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
                <td style="display:none" valign="top">${line.idKandungan}</td>
                <td valign="top">
                    <c:if test="${!actionBean.viewMMKN}">
                        <c:out value="7.${k}"/>
                    </c:if>
                    <c:if test="${actionBean.viewMMKN}">
                        <c:out value="7.${k}"/>
                    </c:if>
                </td>
                <td><s:textarea  id="kandungan12${i}"name="penutup[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/>
                    <s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button>
                </td>

            </tr>
            <c:set var="i" value="${i+1}" />
            <c:set var="k" value="${k+1}" />
        </c:forEach>

        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td></td>
            <td  align="left">
                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('12')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('12',${i-1})"></s:button>
            </td>
        </tr>
        <tr>
            <td align="right" colspan="4"><b>RAHSIA</b></td>
        </tr>
    </table>
</s:form>