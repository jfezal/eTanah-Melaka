<%-- 
    Document   : kertas_siasaatn_N9
    Created on : Jan 11, 2012, 3:50:04 PM
    Author     : sitinorajar
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

        <script type="text/javascript">
            function addRow(index,f)
            {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmnLSTP?tambahRow&index='+index,q,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }

            function addRow1(index,f)
            {
                var q = $(f).formSerialize();
                $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmnLSTP?tambahRow1&index='+index,q,
                function(data){
                    $('#page_div').html(data);
                }, 'html');
            }

            function deleteRow(idKandungan,f)
            {
                if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/minit_mesyuarat_jksmnLSTP?deleteRow&idKandungan='+idKandungan,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }
            }

            $(document).ready(function() {
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $("#close").click( function(){
                    setTimeout(function(){
                        self.close();
                    }, 100);
                });
            });

            function validation() {
                alert("validate");

                var jam = $("#jam").val();
                alert("jam : "+ jam);
                var minit = $("#minit").val();
                alert("minit : "+ minit);
                var ampm = $("#ampm").val();
                alert("ampm : "+ ampm);
                    
                return true;
            }

            function muatNaikForm1(folderId, idPermohonan, dokumenKod, idRujukan) {
                //alert("idRujukan : "+idRujukan);
                var left = (screen.width/2)-(1000/2);
                var top = (screen.height/2)-(150/2);
                var url = '${pageContext.request.contextPath}/penguatkuasaan/upload_action?popupUpload&folderId=' + folderId
                    + '&idPermohonan=' + idPermohonan + '&dokumenKod=' + dokumenKod + '&idRujukan=' + idRujukan;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=200, left=" + left + ",top=" + top);
            }

            function doViewReport(v) {
                var url = '${pageContext.request.contextPath}/dokumen/view/' + v;
                window.open(url, 'etanah', "status=0,toolbar=0,location=0,menubar=0,width=1000,height=800");
            }

            function removeImej(idImej) {
                if(confirm('Adakah anda pasti untuk hapus?')) {
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/maklumat_laporan_operasi?deleteSelected&idImej='+idImej;
                    $.get(url,
                    function(data){
                        $('#page_div').html(data);
                        refreshPage();
                    },'html');
                }
            }
        </script>
    </head>
    <body>
        <s:form beanclass="etanah.view.stripes.pelupusan.MinitMesyuaratJKSMNLSTPActionBean">
            <s:messages/>
            <fieldset class="aras1">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <table align="center">
                        <tr>
                            <td colspan="4" align="center">
                                <p>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                        <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN PERMIT MENCARIGALI DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 41 ENAKMEN MINERAL NEGERI 2002</span>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'PJLB'}">
                                        <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JKSMN BAGI PERMOHONAN LESEN PAJAKAN LOMBONG DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK ${actionBean.permohonan.sebab} <br> ${actionBean.hakmilikPermohonan.hakmilik.lot.nama} ${actionBean.hakmilikPermohonan.hakmilik.noLot} ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} MENGIKUT SEKSYEN 63 ENAKMEN MINERAL NEGERI 2002</span>
                                        </c:if>
                                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LSTP' or actionBean.permohonan.kodUrusan.kod eq 'LPJH' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                                        <span style="text-transform: uppercase;color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">MINIT MESYUARAT JAWATANKUASA SUMBER MINERAL NEGERI <br> NEGERI SEMBILAN BIL <s:text name="noRuj" size="10" id="noRuj"/></span>
                                        </c:if>
                                </p>
                            </td>
                        </tr>
                    </table>

                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <table align="center">
                        <tr>
                            <td>
                                TARIKH : 
                            </td>
                            <td>
                                <s:text name="tarikhSidang" id="datepicker" class="datepicker" size="12" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                TEMPAT :
                            </td>
                            <td>
                                <s:textarea name="tempatSidang" id="tempatSidang" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                MASA :
                            </td>
                            <td>
                                <%--<fmt:formatDate pattern="hh:mm" value="${actionBean.jam}"/>
                                <fmt:formatDate pattern="aaa" value="${actionBean.minit}" var="time"/>
                                <c:if test="${time eq 'AM'}">PAGI</c:if>
                                <c:if test="${time eq 'PM'}">PETANG</c:if>--%>
                                <s:select name="jam" style="width:50px" id="jam">
                                    <s:option value="">Jam</s:option>
                                    <s:option value="00">00</s:option>
                                    <s:option value="1">01</s:option>
                                    <s:option value="2">02</s:option>
                                    <s:option value="3">03</s:option>
                                    <s:option value="4">04</s:option>
                                    <s:option value="5">05</s:option>
                                    <s:option value="6">06</s:option>
                                    <s:option value="7">07</s:option>
                                    <s:option value="8">08</s:option>
                                    <s:option value="9">09</s:option>
                                    <s:option value="10">10</s:option>
                                    <s:option value="11">11</s:option>
                                    <s:option value="12">12</s:option>
                                </s:select>
                                <s:select name="minit" style="width:60px" id="minit">
                                    <s:option value="">Minit</s:option>
                                    <s:option value="00">00</s:option>
                                    <s:option value="05">05</s:option>
                                    <s:option value="10">10</s:option>
                                    <s:option value="15">15</s:option>
                                    <s:option value="20">20</s:option>
                                    <s:option value="25">25</s:option>
                                    <s:option value="30">30</s:option>
                                    <s:option value="35">35</s:option>
                                    <s:option value="40">40</s:option>
                                    <s:option value="45">45</s:option>
                                    <s:option value="50">50</s:option>
                                    <s:option value="55">55</s:option>
                                </s:select>
                                <s:select name="ampm" style="width:60px" id="ampm">
                                    <s:option value="">Pilih</s:option>
                                    <s:option value="AM">AM</s:option>
                                    <s:option value="PM">PM</s:option>
                                </s:select>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <%--<td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.</b></td>--%>
                            <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">KEPUTUSAN : </b></div></td>
                        </tr>

                        <c:set var="i" value="1" />
                        <c:forEach items="${actionBean.mohonKertasKandListOne}" var="line" begin="0">
                            <s:hidden  name="1idMohonKertas${i}" id="1idMohonKertas${i}" value="${line.idKandungan}"/>
                            <tr>
                                <td>&nbsp;</td>
                                <%--<b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${i}</b>--%>
                                <td valign="top"></td>
                                <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${i}" class="normal_text" value="${line.kandungan}"/></td>
                                <%--<td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button></td>--%>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>

                        <c:if test="${actionBean.edit}">
                            <tr>
                                <td>&nbsp;</td>
                                <td valign="top"></td>
                                <td width="100%"><s:textarea rows="6" cols="150" name="1kandungan${fn:length(actionBean.mohonKertasKandListOne)+1}" class="normal_text"/></td>
                                <%--<td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('1')"></s:button></td>--%>
                            </tr>
                        </c:if>

                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2">
                                <%--<s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('1',this.form)"></s:button>--%>
                                <%--<s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('1',this.form)"></s:button>--%>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">&nbsp;</td>
                        </tr>

                        <c:if test="${asal}">
                            <tr>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">TURUT HADIR : </b></div></td>
                            </tr>
                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListTwo}" var="line1" begin="0">
                                <s:hidden  name="2idMohonKertas${i}" id="2idMohonKertas${i}" value="${line1.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <%--<b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${i}</b>--%>
                                    <td valign="top"></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${i}" class="normal_text" value="${line1.kandungan}"/></td>
                                    <%--<td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line1.idKandungan},this.form)"></s:button></td>--%>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.edit1}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <%--<b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${fn:length(actionBean.mohonKertasKandListTwo)+1}</b>--%>
                                    <td valign="top"></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${fn:length(actionBean.mohonKertasKandListTwo)+1}" class="normal_text"/></td>
                                    <%--<td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('2')"></s:button></td>--%>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <%--<s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2',this.form)"></s:button>--%>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('2',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">UCAPAN ALUAN OLEH Y.A.B DATO' SERI PENGERUSI</b></div></td>
                            </tr>

                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListThree}" var="line" begin="0">
                                <s:hidden  name="3idMohonKertas${i}" id="3idMohonKertas${i}" value="${line.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>

                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${i}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${i}" class="normal_text" value="${line.kandungan}"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan},this.form)"></s:button></td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>

                            <c:if test="${actionBean.edit2}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${fn:length(actionBean.mohonKertasKandListThree)+1}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${fn:length(actionBean.mohonKertasKandListThree)+1}" class="normal_text"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('3')"></s:button></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('3',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">PENGESAHAN MINIT MESYUARAT</b></div></td>
                            </tr>


                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListFour}" var="line2" begin="0">
                                <s:hidden  name="4idMohonKertas${i}" id="4idMohonKertas${i}" value="${line2.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${i}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line2.idKandungan},this.form)"></s:button></td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.edit3}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${fn:length(actionBean.mohonKertasKandListFour)+1}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${fn:length(actionBean.mohonKertasKandListFour)+1}" class="normal_text"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('4')"></s:button></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('4',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">PEMBENTANGAN PERMOHONAN-PERMOHONAN DI BAWAH ENAKMEN MINERAL NEGERI 2002</b></div></td>
                            </tr>


                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListFive}" var="line4" begin="0">
                                <s:hidden  name="5idMohonKertas${i}" id="5idMohonKertas${i}" value="${line4.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="5kandungan${i}" class="normal_text" value="${line4.kandungan}"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line4.idKandungan},this.form)"></s:button></td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.edit4}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${fn:length(actionBean.mohonKertasKandListFive)+1}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="5kandungan${fn:length(actionBean.mohonKertasKandListFive)+1}" class="normal_text"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('5')"></s:button></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('5',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('5',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">HAL-HAL LAIN</b></div></td>
                            </tr>


                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListSix}" var="line5" begin="0">
                                <s:hidden  name="6idMohonKertas${i}" id="6idMohonKertas${i}" value="${line5.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${i}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="6kandungan${i}" class="normal_text" value="${line5.kandungan}"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line5.idKandungan},this.form)"></s:button></td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.edit5}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${fn:length(actionBean.mohonKertasKandListSix)+1}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="6kandungan${fn:length(actionBean.mohonKertasKandListSix)+1}" class="normal_text"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('6')"></s:button></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('6',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('6',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                            <tr>
                                <td width="1%"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.</b></td>
                                <td colspan="3"><div align="left"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">PENANGGUHAN MESYUARAT</b></div></td>
                            </tr>


                            <c:set var="i" value="1" />
                            <c:forEach items="${actionBean.mohonKertasKandListSeven}" var="line6" begin="0">
                                <s:hidden  name="7idMohonKertas${i}" id="7idMohonKertas${i}" value="${line6.idKandungan}"/>
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.${i}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="7kandungan${i}" class="normal_text" value="${line6.kandungan}"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line6.idKandungan},this.form)"></s:button></td>
                                </tr>
                                <c:set var="i" value="${i+1}" />
                            </c:forEach>
                            <c:if test="${actionBean.edit6}">
                                <tr>
                                    <td>&nbsp;</td>
                                    <td valign="top"><b style="color:#000000;font-weight:bold;font-family:Tahoma;font-size: 13px;">5.${fn:length(actionBean.mohonKertasKandListSeven)+1}</b></td>
                                    <td width="50%"><s:textarea rows="6" cols="150" name="7kandungan${fn:length(actionBean.mohonKertasKandListSeven)+1}" class="normal_text"/></td>
                                    <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('7')"></s:button></td>
                                </tr>
                            </c:if>
                            <tr>
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7',this.form)"></s:button>
                                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('7',this.form)"></s:button>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="4">&nbsp;</td>
                            </tr>
                        </c:if>
                    </table>




                    <!--                    <tr>
                                                <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                                                <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">SYOR</b></div></td>
                                        </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListFour}" var="line3" begin="0">
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
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.${fn:length(actionBean.mohonKertasKandListFour)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="4kandungan${fn:length(actionBean.mohonKertasKandListFour)+1}" class="normal_text"/></td>
                            <td align="left"><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow('4')"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td colspan="2"> <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4',this.form)"></s:button>
                    <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('4',this.form)"></s:button></td>
</tr>-->
                    ${senarai.dokumen.kodDokumen.nama}
                    <p align="center">
                        <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'LSTP' or actionBean.permohonan.kodUrusan.kod eq 'LPJH' or actionBean.permohonan.kodUrusan.kod eq 'PCRG'}">
                            <%--<s:button name="simpanMohonKertas" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div');"></s:button>--%>
                            <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="addRow1('1',this.form)"></s:button>
                        </c:if>
                        <s:button name="" value="Muat Naik/Papar" class="btn" onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                  '${actionBean.permohonan.idPermohonan}','LO','${line.idOperasi}');return false;"></s:button>

                        <%--<display:column title="Muat Naik/Papar">--%>
                        <%--<img src="${pageContext.request.contextPath}/pub/images/icons/upload.png"
                             onclick="muatNaikForm1('${actionBean.permohonan.folderDokumen.folderId}',
                                 '${actionBean.permohonan.idPermohonan}','LO','${line.idOperasi}');return false;" height="30" width="30" alt="Muat Naik"
                             onmouseover="this.style.cursor='pointer';" title="Muat Naik untuk Dokumen"/>--%>

                    <p align="center">
                        <c:set value="1" var="count"/>
                        <c:forEach items="${actionBean.permohonan.folderDokumen.senaraiKandungan}" var="senarai">
                            <c:if test="${senarai.dokumen.kodDokumen.kod eq 'LO' && senarai.dokumen.perihal eq line.idOperasi}">
                                <c:if test="${senarai.dokumen.namaFizikal != null}">
                                    <br>

                                    <img src="${pageContext.request.contextPath}/pub/images/icons/_active__document.png"
                                         onclick="doViewReport('${senarai.dokumen.idDokumen}');" height="30" width="30" alt="papar"
                                         onmouseover="this.style.cursor='pointer';" title="Papar Dokumen ${senarai.dokumen.kodDokumen.nama}"/>
                                    ${count}-${senarai.dokumen.tajuk}/
                                    <img src='${pageContext.request.contextPath}/images/not_ok.gif'
                                         onclick="removeImej('${senarai.dokumen.idDokumen}');" height="15" width="15" alt="Hapus"
                                         onmouseover="this.style.cursor='pointer';" title="Hapus untuk Dokumen"/>

                                    <c:set value="${count+1}" var="count"/>
                                </c:if>
                            </c:if>

                        </c:forEach>
                    </p>
                    <%--</display:column>--%>
                    </p>

                </table>
            </fieldset>
        </s:form>
    </body>
</html>
