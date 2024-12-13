<%-- 
    Document   : Kertas_MMKN
    Created on : Jul 9, 2010, 12:44:34 PM
    Author     : Rohan
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
        var row = table.insertRow(rowcount);

        var leftcell = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        leftcell.appendChild(bil);

        var rightcell = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'ulasan' + rowcount;
        el.rows = 5;
        el.cols = 165;
        el.style.textTransform = 'uppercase';
        rightcell.appendChild(el);
    }

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);

        var leftcell1 = row.insertCell(0);
        var bil = document.createTextNode(rowcount);
        leftcell1.appendChild(bil);

        var rightcell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihalTanah' + rowcount;
        el.rows = 5;
        el.cols = 165;
        el.style.textTransform = 'uppercase';
        rightcell1.appendChild(el);
    }

</script>
<s:form beanclass="etanah.view.stripes.pembangunan.melaka.KertasMMKNActionBean">
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <div class="content" align="center">
                <table border="0" width="95%">
                    <tr><td align="center"><b>MAJLIS MESYURAT KERAJAAN NEGERI MELAKA</b></td></tr>

                    <tr><td>
                            <table border="0" width="80%" cellspacing="10">
                                <tr><td width="15"><b>PERSIDANGAN</b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit}">
                                        <td><b><font color="red">*</font> <s:text name="permohonanRujukanLuar.noSidang" size="20"/></b></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td>${actionBean.permohonanRujukanLuar.noSidang}</td>
                                    </c:if>
                                </tr>
                                <tr><td><b>MASA</b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit}">
                                        <td><b><font color="red">*</font> <s:text name="permohonanRujukanLuar.tarikhSidang" id="datepicker1" class="datepicker" size="20" /></b></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td>${actionBean.permohonanRujukanLuar.tarikhSidang}</td>
                                    </c:if>
                                </tr>
                                <tr><td><b>TARIKH</b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit}">

                                        <td><b><font color="red">*</font> <s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="20" /></b></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td> -- NO DATA -- </td>

                                    </c:if>
                                </tr>
                                <tr><td><b>TEMPAT</b></td>
                                    <td><b>:</b></td>
                                    <c:if test="${edit}">
                                        <td><b><font color="red">*</font> <s:textarea name="permohonanRujukanLuar.lokasiAgensi" rows="3" cols="50"/></b></td>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <td>${actionBean.permohonanRujukanLuar.lokasiAgensi}</td>
                                    </c:if>
                                </tr>
                            </table></td></tr> <br>

                    <tr><td><b><font style="text-transform: uppercase">PERMOHONAN PENARIKAN BALIK DARIPADA PENGAMBILAN SEBAHAGIAN TANAH DI LOT ${tbl1.hakmilik.lot.nama} ${tbl1.hakmilik.noLot},MUKIM BUKIT BARU DAERAH $ TENGAH BAGI PROJEK MENAIKTARAF TALIAN PENGHANTARAN ELEKTRIK $
                                    DARI PENCAWANG MASUK UTAMA SEMABOK KE PENCAWANG MASUK UTAMA AYER KEROH ,MELAKA. </font></b></td></tr>

                    <tr align="left"><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform: none">1.1<br><s:textarea rows="5" cols="165" name="tujuan"/></font></td></tr>
                                </c:if>
                                <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none">1.1 &nbsp; ${actionBean.tujuan}</font></td></tr>
                    </c:if>

                    <tr><td><b>2.LATAR BELAKANG</b></td></tr>
                    <tr><td>
                            <table id="tbl" class="tablecloth">
                                <c:if test="${edit}">

                                    <tr>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${actionBean.ulasan1 eq null}">
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="ulasan1" cols="165" rows="5"/>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="ulasan1" rows="5" cols="165">${actionBean.ulasan1}</s:textarea>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${actionBean.ulasan2 ne null}">
                                        <tr>
                                            <td>2</td>
                                            <td><s:textarea name="ulasan2" rows="5" cols="165">${actionBean.ulasan2}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan3 ne null}">
                                        <tr>
                                            <td>3</td>

                                            <td><s:textarea name="ulasan3" rows="5" cols="165">${actionBean.ulasan3}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan4 ne null}">
                                        <tr>
                                            <td>4</td>

                                            <td><s:textarea name="ulasan4" rows="5" cols="165">${actionBean.ulasan4}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan5 ne null}">
                                        <tr>
                                            <td>5</td>

                                            <td><s:textarea name="ulasan5" rows="5" cols="165">${actionBean.ulasan5}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan6 ne null}">
                                        <tr>
                                            <td>6</td>

                                            <td><s:textarea name="ulasan6" rows="5" cols="165">${actionBean.ulasan6}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan7 ne null}">
                                        <tr>
                                            <td>7</td>

                                            <td><s:textarea name="ulasan7" rows="5" cols="165">${actionBean.ulasan7}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan8 ne null}">
                                        <tr>
                                            <td>8</td>
                                            <td><s:textarea name="ulasan8" rows="5" cols="165">${actionBean.ulasan8}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan9 ne null}">
                                        <tr>
                                            <td>9</td>
                                            <td><s:textarea name="ulasan9" rows="5" cols="165">${actionBean.ulasan9}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan10 ne null}">
                                        <tr>
                                            <td>10</td>
                                            <td><s:textarea name="ulasan10" rows="5" cols="165">${actionBean.ulasan10}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.ulasan11 ne null}">
                                        <tr>
                                            <td>11</td>
                                            <td><s:textarea name="ulasan11" rows="5" cols="165">${actionBean.ulasan11}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:if>

                            </table><c:if test="${!edit}">
                                <display:table name="${actionBean.listUlasan2}" >

                                </display:table>
                            </c:if>
                            <c:if test="${actionBean.btn}">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow('tbl')"/>&nbsp;
                            </c:if>
                        </td></tr>

                    <tr><td><b>3.PERIHAL TANAH</b></td></tr>
                    <tr><td>
                            <c:if test="${edit}">
                                <table id="tb2" class="tablecloth">
                                    <tr>

                                    </tr>
                                    <c:choose>
                                        <c:when test="${actionBean.perihalTanah1 eq null}">
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="perihalTanah1" cols="165" rows="5"/>
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td>1</td>
                                                <td><s:textarea name="perihalTanah1" rows="5" cols="165">${actionBean.perihalTanah1}</s:textarea>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:if test="${actionBean.perihalTanah2 ne null}">
                                        <tr>
                                            <td>2</td>
                                            <td><s:textarea name="perihalTanah2" rows="5" cols="165">${actionBean.perihalTanah2}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah3 ne null}">
                                        <tr>
                                            <td>3</td>

                                            <td><s:textarea name="perihalTanah3" rows="5" cols="165">${actionBean.perihalTanah3}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah4 ne null}">
                                        <tr>
                                            <td>4</td>

                                            <td><s:textarea name="perihalTanah4" rows="5" cols="165">${actionBean.perihalTanah4}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah5 ne null}">
                                        <tr>
                                            <td>5</td>

                                            <td><s:textarea name="perihalTanah5" rows="5" cols="165">${actionBean.perihalTanah5}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah6 ne null}">
                                        <tr>
                                            <td>6</td>

                                            <td><s:textarea name="perihalTanah6" rows="5" cols="165">${actionBean.perihalTanah6}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah7 ne null}">
                                        <tr>
                                            <td>7</td>

                                            <td><s:textarea name="perihalTanah7" rows="5" cols="165">${actionBean.perihalTanah7}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah8 ne null}">
                                        <tr>
                                            <td>8</td>
                                            <td><s:textarea name="perihalTanah8" rows="5" cols="165">${actionBean.perihalTanah8}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah9 ne null}">
                                        <tr>
                                            <td>9</td>
                                            <td><s:textarea name="perihalTanah9" rows="5" cols="165">${actionBean.perihalTanah9}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah10 ne null}">
                                        <tr>
                                            <td>10</td>
                                            <td><s:textarea name="perihalTanah10" rows="5" cols="165">${actionBean.perihalTanah10}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${actionBean.perihalTanah11 ne null}">
                                        <tr>
                                            <td>11</td>
                                            <td><s:textarea name="perihalTanah11" rows="5" cols="165">${actionBean.perihalTanah11}</s:textarea>
                                            </td>
                                        </tr>
                                    </c:if>
                                </table>
                            </c:if>
                            <c:if test="${!edit}">
                                <display:table name="${actionBean.listPerihalTanah}" >

                                </display:table>
                            </c:if>
                            <c:if test="${actionBean.btn}">
                                <s:button class="btn" value="Tambah" name="add" onclick="addRow2('tb2')"/>&nbsp;
                            </c:if>
                        </td></tr>

                    <tr><td><b>4.PERAKUAN PENTADBIR TANAH MELAKA TENGA</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td><font style="text-transform:none"><s:textarea rows="5" cols="165" name="bentuk"/></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none"></font></td></tr>
                    </c:if>

                    <tr><td><b>5.PERAKUAN PENGARAH TANAH DAN GALIAN</b></td></tr>

                    <c:if test="${edit}">
                        <tr><td><font style="text-transform:none"><s:textarea rows="5" cols="165" name="perakun"/></font></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><font style="text-transform:none"></font></td></tr>
                    </c:if>

                </table>
                <c:if test="${edit}">
                    <br>
                    <td align="center">
                        <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                    </td>
                </c:if>
            </div>

        </fieldset>
    </div>
</s:form>