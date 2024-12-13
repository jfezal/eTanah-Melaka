<%-- 
    Document   : kertas_makluman_N9
    Created on : Jan 11, 2012, 4:29:46 PM
    Author     : Srinivas
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
                    $.post('${pageContext.request.contextPath}/pelupusan/kertas_makluman_ns?tambahRow&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function addRow1(index,f)
                {
                    var q = $(f).formSerialize();
                    $.post('${pageContext.request.contextPath}/pelupusan/kertas_makluman_ns?tambahRow1&index='+index,q,
                    function(data){
                        $('#page_div').html(data);
                    }, 'html');
                }

                function deleteRow(idKandungan,f)
                {
                    if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
                         var q = $(f).formSerialize();
                        $.post('${pageContext.request.contextPath}/pelupusan/kertas_makluman_ns?deleteRow&idKandungan='+idKandungan,q,
                        function(data){
                            $('#page_div').html(data);
                        }, 'html');
                    }
                }
         </script>
    </head>
    <body>
       <s:form beanclass="etanah.view.stripes.pelupusan.KertasMaklumanN9ActionBean">
           <s:messages/>
       <fieldset class="aras1">
                <table width="100%" border="0">
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center">
                            <p>
                             <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;" >Kertas Makluman Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus </span>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4" align="center" >
                            <p>
                              <span style="text-transform: uppercase;color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">PERMOHONAN DARIPADA ${actionBean.pemohon.pihak.nama} UNTUK MEMILIKI TANAH KERAJAAN SECARA HAKMILIK TETAP DI BAWAH SEKSYEN 76 KANUN TANAH NEGARA 1965 SELUAS LBIH KURANG ${actionBean.hakmilikPermohonan.luasTerlibat} DI ${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama} DAERAH ${actionBean.hakmilikPermohonan.hakmilik.cawangan.daerah.nama} UNTUK KEGUNAAN ${actionBean.permohonan.sebab}</span>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">TUJUAN</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListOne}" var="line" begin="0">
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
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">1.${fn:length(actionBean.mohonKertasKandListOne)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="1kandungan${fn:length(actionBean.mohonKertasKandListOne)+1}" class="normal_text"/></td>
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
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">LATAR BELAKANG TANAH</b></div></td>
                    </tr>
                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListTwo}" var="line1" begin="0">
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
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">2.${fn:length(actionBean.mohonKertasKandListTwo)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="2kandungan${fn:length(actionBean.mohonKertasKandListTwo)+1}" class="normal_text"/></td>
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
                    <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">MAKLUMAN PENGARAH TANAH DAH GALAM NEGERI SEMBILAN</b></div></td>
                    </tr>

                    <c:set var="i" value="1" />
                    <c:forEach items="${actionBean.mohonKertasKandListThree}" var="line2" begin="0" end="0">
                    <s:hidden  name="3idMohonKertas${i}" id="3idMohonKertas${i}" value="${line2.idKandungan}"/>
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${i}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${i}" class="normal_text" value="${line2.kandungan}"/></td>
                            <td><s:button value="Kemaskini" class="btn" name="simpan1"  onclick="addRow1('3',this.form)"></s:button></td>
                    </tr>
                    <c:set var="i" value="${i+1}" />
                    </c:forEach>
                    <c:if test="${actionBean.edit23}">
                    <tr>
                            <td>&nbsp;</td>
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.${fn:length(actionBean.mohonKertasKandListThree)+1}</b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan@" class="normal_text"/></td>
                            <td><s:button value="Kemaskini" class="btn" name="simpan1"  onclick="addRow1('3',this.form)"></s:button></td>
                    </tr>
                    </c:if>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <div class="content" align="center">
                                    <table class="tablecloth">
                                        <tr>
                                            <th>&nbsp;</th><th>Nombor Lot</th><th>Kegunaan</th><th>Catatan</th>
                                        </tr>
                                                 <%--UTARA--%>
                                                 <c:if test="${!empty actionBean.listLaporTanahSpdnU}">
                                                         <c:set var="i" value="1" />
                                                         <tr>
                                                                <th rowspan="${actionBean.uSize}">
                                                                    Utara
                                                                </th>
                                                         <c:forEach items="${actionBean.listLaporTanahSpdnU}" var="line">

                                                                <td>
                                                                    ${line.laporanTanahSmpdn.hakmilik.noLot}
                                                                </td>
                                                                <td>
                                                                    ${line.laporanTanahSmpdn.guna}
                                                                </td>
                                                                <td>
                                                                    ${line.laporanTanahSmpdn.catatan}
                                                                </td>

                                                          </tr>
                                                          <c:set var="i" value="${i+1}" />
                                                          </c:forEach>
                                                 </c:if>
                                                 <c:if test="${empty actionBean.listLaporTanahSpdnU}">
                                                    <tr>
                                                           <th>Utara</th>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                    </tr>
                                                 </c:if>
                                            <%--END OF UTARA--%>
                                            <%--SELATAN--%>
                                            <c:if test="${!empty actionBean.listLaporTanahSpdnS}">
                                                <c:set var="i" value="1" />
                                                <tr>
                                                        <th rowspan="${actionBean.sSize}">
                                                           Selatan
                                                        </th>
                                                <c:forEach items="${actionBean.listLaporTanahSpdnS}" var="line">

                                                        <td>
                                                            ${line.laporanTanahSmpdn.hakmilik.noLot}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.guna}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.catatan}
                                                        </td>

                                                  </tr>

                                                  <c:set var="i" value="${i+1}" />
                                                  </c:forEach>
                                            </c:if>
                                            <c:if test="${empty actionBean.listLaporTanahSpdnS}">
                                                <tr>
                                                           <th>Selatan</th>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                </tr>
                                            </c:if>
                                            <%--END OF SELATAN--%>
                                            <%--TIMUR--%>
                                            <c:if test="${!empty actionBean.listLaporTanahSpdnT}">
                                            <c:set var="i" value="1" />
                                                <tr>
                                                        <th rowspan="${actionBean.tSize}">
                                                           Timur
                                                        </th>
                                                <c:forEach items="${actionBean.listLaporTanahSpdnT}" var="line">

                                                        <td>
                                                            ${line.laporanTanahSmpdn.hakmilik.noLot}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.guna}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.catatan}
                                                        </td>

                                                 </tr>

                                                 <c:set var="i" value="${i+1}" />
                                                 </c:forEach>

                                            </c:if>
                                            <c:if test="${empty actionBean.listLaporTanahSpdnT}">
                                                <tr>
                                                           <th>Timur</th>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                </tr>
                                            </c:if>
                                             <%--END OF TIMUR--%>
                                             <%--BARAT--%>
                                            <c:if test="${!empty actionBean.listLaporTanahSpdnB}">
                                                <c:set var="i" value="1" />
                                                <tr>
                                                        <th rowspan="${actionBean.bSize}">
                                                           Barat
                                                        </th>
                                                <c:forEach items="${actionBean.listLaporTanahSpdnB}" var="line">

                                                        <td>
                                                            ${line.laporanTanahSmpdn.hakmilik.noLot}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.guna}
                                                        </td>
                                                        <td>
                                                            ${line.laporanTanahSmpdn.catatan}
                                                        </td>

                                                 </tr>

                                                 <c:set var="i" value="${i+1}" />
                                                 </c:forEach>

                                            </c:if>
                                            <c:if test="${empty actionBean.listLaporTanahSpdnB}">
                                                <tr>
                                                           <th>Barat</th>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                           <td>&nbsp;</td>
                                                </tr>
                                            </c:if>
                                             <%--END OF BARAT--%>

                                    </table>

                          </div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">&nbsp;</td>
                    </tr>
                    <c:set var="i" value="2" />
                    <c:forEach items="${actionBean.mohonKertasKandListThree}" var="line2" begin="1">
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
                            <td valign="top"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">3.<c:if test="${fn:length(actionBean.mohonKertasKandListThree) == 0}">2</c:if><c:if test="${fn:length(actionBean.mohonKertasKandListThree) > 0}">${fn:length(actionBean.mohonKertasKandListThree)+1}</c:if></b></td>
                            <td width="50%"><s:textarea rows="6" cols="150" name="3kandungan${fn:length(actionBean.mohonKertasKandListThree)+1}" class="normal_text"/></td>
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
                    <tr>
                            <td width="1%"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">4.</b></td>
                            <td colspan="3"><div align="left"><b style="color:#003194;font-weight:bold;font-family:Tahoma;font-size: 13px;">Keputusan</b></div></td>
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
                    </tr>
                </table>
      </fieldset>
       </s:form>
    </body>
</html>
