<%-- 
    Document   : pengecualian_Ukur_Rezab
    Created on : Jul 11, 2010, 1:40:39 AM
    Author     : NageswaraRao
--%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

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
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 6){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'perakuanPentadbir' + rowcount;
           el.rows = 5;
           el.cols = 160;
           el.style.textTransform = 'uppercase';
          leftcell.appendChild(el);
        } else{
            alert('Huraian Pentadbir Tanah telah lengkap.');
            $("#syorptd").focus();
            return true;
        }

    }
</script>

<s:form beanclass="etanah.view.stripes.pembangunan.PengecualianUkurRezabActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                           <tr><td><b><font style="text-transform: uppercase">PERMOHONAN DARIPADA  ${actionBean.pemohon.pihak.nama}  UNTUK  ${actionBean.permohonan.kodUrusan.nama} BAGI
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <c:out value="${senaraiHakmilik.hakmilik.lot.nama}"/>
                                            <c:out value="${senaraiHakmilik.hakmilik.noLot}"/>,
                                            <c:out value="${senaraiHakmilik.hakmilik.bandarPekanMukim.nama}"/>, Daerah
                                            <c:out value="${senaraiHakmilik.hakmilik.daerah.nama}"/>,
                                        </c:forEach>
                                        
                                        UNTUK TUJUAN 
                                        <%--<c:set value=" " var="gunaTanah"/>
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiGunaTanah">
                                            <c:if test="${senaraiGunaTanah.hakmilik.kegunaanTanah.nama ne gunaTanah}">
                                                <c:out value="${senaraiGunaTanah.hakmilik.kegunaanTanah.nama}"/>
                                                <c:set value="${senaraiGunaTanah.hakmilik.kegunaanTanah.nama}" var="gunaTanah"/>
                                            </c:if>
                                        </c:forEach>--%>
                                        <c:forEach items="${actionBean.hakmilikPermohonanList}" var="senaraiHakmilik" >
                                            <fmt:formatNumber pattern="#,##0.0000" value="${senaraiHakmilik.hakmilik.luas}"/> ${senaraiHakmilik.hakmilik.kodUnitLuas.nama} &nbsp;
                                        </c:forEach>
                                        .</font></b></td></tr><br>
                        <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="tujuan"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr><br>
                    </c:if>
                        <tr><td><b>2. LATAR BELAKANG</b></td></tr><br>
                        <tr><td><b>2.1 Perihal Permohonan </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="perihalPermohonan"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.1.1 &nbsp; ${actionBean.perihalPermohonan}</font></td></tr><br>
                    </c:if> <br>
                        <tr><td><b>2.2 Perihal Tanah </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="perihalTanah"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.2.1 &nbsp; ${actionBean.perihalTanah}</font></td></tr><br>
                    </c:if> <br>
                    <tr><td>
                            <table width="90%" align="center" class="tablecloth">
                                <tr>
                                    <th>Kedudukan</th>
                                    <th>Tanah Kerajaan/Rizab/Lot/PT</th>
                                    <th>Apa yang Terdapat Di atas Tanah </th>
                                </tr>
                                <tr>
                                    <td>Utara </td>
                                    <td> ${actionBean.laporanTanah.sempadanUtaraNoLot}  </td>
                                    <td> ${actionBean.laporanTanah.sempadanUtaraKegunaan}   </td>
                                </tr>
                                <tr>
                                    <td>Selatan </td>
                                    <td> ${actionBean.laporanTanah.sempadanSelatanNoLot}  </td>
                                    <td> ${actionBean.laporanTanah.sempadanSelatanKegunaan}   </td>
                                </tr>
                                <tr>
                                    <td>Timur </td>
                                    <td> ${actionBean.laporanTanah.sempadanTimurNoLot}  </td>
                                    <td> ${actionBean.laporanTanah.sempadanTimurKegunaan}   </td>
                                </tr>
                                <tr>
                                    <td> Barat </td>
                                    <td> ${actionBean.laporanTanah.sempadanBaratNoLot}  </td>
                                    <td> ${actionBean.laporanTanah.sempadanBaratKegunaan}   </td>
                                </tr>
                            </table>
                        </td></tr> <br>
                        <tr><td><b>2.3 Perihal Permohon </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="perihalPemohon"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">2.3.1 &nbsp; ${actionBean.perihalPemohon}</font></td></tr><br>
                    </c:if> <br>
                        <tr><td><b>3. ULASAN JABATAN-JABATAN TEKNIKAL DAN ADUN KAWASAN </b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: uppercase"><s:textarea rows="5" cols="160" name="ulasan"/></font></td></tr><br>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:capitalize">3.1 &nbsp; ${actionBean.ulasan}</font></td></tr><br>
                    </c:if> <br>
                       
                       <c:if test="${edit}">
                           <tr><td>
                                   <table border="0" width="96%" id="tblhuraian">
                                       <tr><td><b><font style="text-transform: uppercase">4. PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH </font></b></td></tr>
                                       <tr><td><s:textarea name="perakuanPentadbir" rows="5" cols="160"/></td></tr>
                                       <c:if test="${actionBean.perakuanPentadbir2 ne null}">
                                           <tr><td><s:textarea name="perakuanPentadbir2" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.perakuanPentadbir3 ne null}">
                                           <tr><td><s:textarea name="perakuanPentadbir3" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.perakuanPentadbir4 ne null}">
                                           <tr><td><s:textarea name="perakuanPentadbir4" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.perakuanPentadbir5 ne null}">
                                           <tr><td><s:textarea name="perakuanPentadbir5" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                   </table>
                                   <c:if test="${actionBean.btn}">
                                       <s:button class="btn" value="Tambah" name="add" onclick="addRow('tblhuraian')"/>&nbsp;
                                   </c:if>
                               </td>
                           </tr><br>
                           
                       </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b><font style="text-transform: uppercase">4. PERAKUAN PENTADBIR TANAH DAERAH MELAKA TENGAH</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">4.1 ${actionBean.perakuanPentadbir}&nbsp;</font></td></tr><br>
                            <c:if test="${actionBean.perakuanPentadbir2 ne null}">
                                <tr><td><font style="text-transform:capitalize">4.2 ${actionBean.perakuanPentadbir2}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.perakuanPentadbir3 ne null}">
                                <tr><td><font style="text-transform:capitalize">4.3 ${actionBean.perakuanPentadbir3}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.perakuanPentadbir4 ne null}">
                                <tr><td><font style="text-transform:capitalize">4.4 ${actionBean.perakuanPentadbir4}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.perakuanPentadbir5 ne null}">
                                <tr><td><font style="text-transform:capitalize">4.5 ${actionBean.perakuanPentadbir5}&nbsp;</font></td></tr><br>
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