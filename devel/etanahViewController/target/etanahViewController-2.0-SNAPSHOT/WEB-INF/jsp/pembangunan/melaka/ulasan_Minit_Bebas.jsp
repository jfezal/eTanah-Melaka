<%-- 
    Document   : ulasan_Minit_Bebas
    Created on : Feb 24, 2011, 5:53:38 PM
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
</style><script type="text/javascript">
    function addRow(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 8){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'penyediaan' + (parseInt(rowcount)+1);
           //el.value = 'penyediaan' + (parseInt(rowcount)+1);
           //alert(rowcount);
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

    function addRow2(tableid){
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;

        if(rowcount < 6){
           var row = table.insertRow(rowcount);

           var leftcell = row.insertCell(0);
           var el = document.createElement('textarea');
           el.name = 'pentadbir' + rowcount;
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

<s:form beanclass="etanah.view.stripes.pembangunan.UlasanMinitBebasActionBean">
    <s:messages/>
    <s:errors/>
    <s:hidden name="kandunganK.kertas.idKertas"/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Ulasan /Keputusan Minit Bebas</legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                       <c:if test="${edit}">
                           <tr><td>
                                   <table border="0" width="96%" id="tblPenyediaan">
                                       <%--<tr><td><b><font style="text-transform: uppercase">1. Penyediaan Minit Bebas</font></b></td></tr>--%>
                                       <tr><td><s:textarea name="penyediaan" rows="5" cols="160"/></td></tr>
                                       <c:if test="${actionBean.penyediaan2 ne null}">
                                           <tr><td><s:textarea name="penyediaan2" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan3 ne null}">
                                           <tr><td><s:textarea name="penyediaan3" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan4 ne null}">
                                           <tr><td><s:textarea name="penyediaan4" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan5 ne null}">
                                           <tr><td><s:textarea name="penyediaan5" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan6 ne null}">
                                           <tr><td><s:textarea name="penyediaan6" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan7 ne null}">
                                           <tr><td><s:textarea name="penyediaan7" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.penyediaan8 ne null}">
                                           <tr><td><s:textarea name="penyediaan8" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                   </table>
                                   <c:if test="${actionBean.btn}">
                                       <s:button class="btn" value="Tambah" name="add" onclick="addRow('tblPenyediaan')"/>&nbsp;
                                   </c:if>
                               </td>
                           </tr><br>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b><font style="text-transform: uppercase">1. Penyediaan Minit Bebas</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">1.1 ${actionBean.penyediaan}&nbsp;</font></td></tr><br>
                            <c:if test="${actionBean.penyediaan2 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.2 ${actionBean.penyediaan2}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan3 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.3 ${actionBean.penyediaan3}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan4 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.4 ${actionBean.penyediaan4}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan5 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.5 ${actionBean.penyediaan5}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan6 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.6 ${actionBean.penyediaan6}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan7 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.7 ${actionBean.penyediaan7}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.penyediaan8 ne null}">
                                <tr><td><font style="text-transform:capitalize">1.8 ${actionBean.penyediaan8}&nbsp;</font></td></tr><br>
                            </c:if>
                         </c:if>
                        <%--<c:if test="${edit}">
                           <tr><td>
                                   <table border="0" width="96%" id="tblPentadbir">
                                       <tr><td><b><font style="text-transform: uppercase">2. Syor Penolong Pentadbir</font></b></td></tr>
                                       <tr><td><s:textarea name="pentadbir" rows="5" cols="160"/></td></tr>
                                       <c:if test="${actionBean.pentadbir2 ne null}">
                                           <tr><td><s:textarea name="pentadbir2" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.pentadbir3 ne null}">
                                           <tr><td><s:textarea name="pentadbir3" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.pentadbir4 ne null}">
                                           <tr><td><s:textarea name="pentadbir4" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                       <c:if test="${actionBean.pentadbir5 ne null}">
                                           <tr><td><s:textarea name="pentadbir5" rows="5" cols="160"/></td></tr><br>
                                       </c:if>
                                   </table>
                                   <c:if test="${actionBean.btn}">
                                       <s:button class="btn" value="Tambah" name="add" onclick="addRow2('tblPentadbir')"/>&nbsp;
                                   </c:if>
                               </td>
                           </tr><br>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b><font style="text-transform: uppercase">2. Syor Penolong Pentadbir</font></b></td></tr>
                            <tr><td><font style="text-transform:capitalize">2.1 ${actionBean.pentadbir}&nbsp;</font></td></tr><br>
                            <c:if test="${actionBean.pentadbir2 ne null}">
                                <tr><td><font style="text-transform:capitalize">2.2 ${actionBean.pentadbir2}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.pentadbir3 ne null}">
                                <tr><td><font style="text-transform:capitalize">2.3 ${actionBean.pentadbir3}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.pentadbir4 ne null}">
                                <tr><td><font style="text-transform:capitalize">2.4 ${actionBean.pentadbir4}&nbsp;</font></td></tr><br>
                            </c:if>
                            <c:if test="${actionBean.pentadbir5 ne null}">
                                <tr><td><font style="text-transform:capitalize">2.5 ${actionBean.pentadbir5}&nbsp;</font></td></tr><br>
                            </c:if>
                         </c:if>
                        <c:if test="${edit}">
                            <tr><td><b>3. Kelulusan Pentadbir Tanah</b></td></tr>
                            <tr><td><s:textarea name="kelulusan" rows="5" cols="160"/></td></tr><br>
                        </c:if>
                        <c:if test="${!edit}">
                            <tr><td><b>3. Kelulusan Pentadbir Tanah</b></td></tr>
                            <tr><td><font style="text-transform:capitalize"> ${actionBean.kelulusan}&nbsp;</font></td></tr><br>
                        </c:if> --%>
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