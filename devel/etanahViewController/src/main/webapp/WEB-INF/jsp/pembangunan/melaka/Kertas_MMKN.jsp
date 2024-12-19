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

    function addRow1(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2.1." + (rowcount + 1) + "</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihalPermohonan' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        el.id = 'perihalPermohonan' + (rowcount + 1);
        cell1.appendChild(el);
        document.getElementById("rowCount1").value = rowcount + 1;
    }

    function addRow2(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2.2." + (rowcount + 1) + "</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihalTanah' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount2").value = rowcount + 1;
    }

    function addRow3(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b> 2.3." + (rowcount + 1) + "</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perihalPemohon' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount3").value = rowcount + 1;
    }


    function addRow4(tableid) {
        var table = document.getElementById(tableid);
        var rowcount = table.rows.length;
        var row = table.insertRow(rowcount);
        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<b>4." + (rowcount + 1) + "</b>";
        var cell1 = row.insertCell(1);
        var el = document.createElement('textarea');
        el.name = 'perakuanPtd' + (rowcount + 1);
        el.rows = 5;
        el.cols = 150;
        cell1.appendChild(el);
        document.getElementById("rowCount4").value = rowcount + 1;
    }


    function deleteRow1()
    {
        var j = document.getElementById('rowCount1').value;
        if (j == 1) {
            alert("Tiada Boleh Dihapuskan");
            return false;
        }
        var x = document.getElementById('dataTable1').rows[j - 1].cells;
        var idKandungan = x[0].innerHTML;
        document.getElementById('dataTable1').deleteRow(j - 1);
        var rc1 = $('#rowCount1').val();
        var c1 = $('#count1').val();
    <%--alert("rc1:"+rc1);
    alert("c1:"+c1);--%>
                    if (rc1 == c1) {
                        var url = '${pageContext.request.contextPath}/pembangunan/melaka/Kertas_MMKN?deleteSingle&idKandungan='
                                + idKandungan;
                        $.get(url,
                                function(data) {
                                    $('#page_div').html(data);
                                }, 'html');
                    }
                    document.getElementById('rowCount1').value = j - 1;
                }

                function deleteRow2()
                {
                    var j = document.getElementById('rowCount2').value;
                    if (j == 1) {
                        alert("Tiada Boleh Dihapuskan");
                        return false;
                    }
                    var x = document.getElementById('dataTable2').rows[j - 1].cells;
                    var idKandungan = x[0].innerHTML;
                    document.getElementById('dataTable2').deleteRow(j - 1);
                    var rc2 = $('#rowCount2').val();
                    var c2 = $('#count2').val();
    <%--alert("rc1:"+rc2);
    alert("c1:"+c2);--%>
                if (rc2 == c2) {
                    var url = '${pageContext.request.contextPath}/pembangunan/melaka/Kertas_MMKN?deleteSingle&idKandungan='
                            + idKandungan;
    <%--alert(url);--%>
                    $.get(url,
                            function(data) {
                                $('#page_div').html(data);
                            }, 'html');
                }
                document.getElementById('rowCount2').value = j - 1;
            }

            function deleteRow3()
            {
                var j = document.getElementById('rowCount3').value;
                if (j == 1) {
                    alert("Tiada Boleh Dihapuskan");
                    return false;
                }
                var x = document.getElementById('dataTable3').rows[j - 1].cells;
                var idKandungan = x[0].innerHTML;
                document.getElementById('dataTable3').deleteRow(j - 1);
                var rc3 = $('#rowCount3').val();
                var c3 = $('#count3').val();
    <%--alert("rc3:"+rc3);
    alert("c3:"+c3);--%>
                    if (rc3 == c3) {
                        var url = '${pageContext.request.contextPath}/pembangunan/melaka/Kertas_MMKN?deleteSingle&idKandungan='
                                + idKandungan;
    <%--alert("url:"+url);--%>
                            $.get(url,
                                    function(data) {
                                        $('#page_div').html(data);
                                    }, 'html');
                        }
                        document.getElementById('rowCount3').value = j - 1;
                    }


                    function deleteRow4()
                    {
                        var j = document.getElementById('rowCount4').value;
                        if (j == 1) {
                            alert("Tiada Boleh Dihapuskan");
                            return false;
                        }
                        var x = document.getElementById('dataTable4').rows[j - 1].cells;
                        var idKandungan = x[0].innerHTML;
                        document.getElementById('dataTable4').deleteRow(j - 1);
                        var rc4 = $('#rowCount4').val();
                        var c4 = $('#count4').val();
    <%--alert("rc4:"+rc4);
    alert("c4:"+c4);--%>
                    if (rc4 == c4) {
                        var url = '${pageContext.request.contextPath}/pembangunan/melaka/Kertas_MMKN?deleteSingle&idKandungan='
                                + idKandungan;
    <%--alert("url:"+url);--%>
                            $.get(url,
                                    function(data) {
                                        $('#page_div').html(data);
                                    }, 'html');
                        }
                        document.getElementById('rowCount4').value = j - 1;
                    }



                    function validation(event, f) {
                        $('#dialog-confirm').dialog('open')
                        $(function() {
                            $("#dialog-confirm").dialog({
                                resizable: false,
                                height: 140,
                                modal: true,
                                buttons: {
                                    "Tidak": function() {
                                        //  alert('tidak selected');
                                        $(this).dialog("close");
                                        return false;
                                    },
                                    "Ya": function() {
                                        // alert('ya selected');
                                        $(this).dialog("close");

                                        var q = $(f).formSerialize();
                                        var url = f.action + '?' + event;
                                        $.post(url, q,
                                                function(data) {
                                                    $('#page_div', this.document).html(data);
                                                }, 'html');
                                        return true;
                                    }
                                }
                            });
                        });

                    }

</script>

<s:form beanclass="etanah.view.stripes.pembangunan.KertasMMKNActionBean">
    <s:hidden name="kertasK.kertas.idKertas"/>
    <s:hidden name="btn"/>

    <s:errors/>
    <s:messages/>
    <div id="dialog-confirm" title="Kertas MMKN" style="display:none">
        <p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
        <font size="3"> Adakah anda pasti untuk menjana Kertas MMKN baru?</font>
    </p>
</div>

<div class="subtitle">
    <fieldset class="aras1">
        <c:if test="${actionBean.permohonan eq null}">
            <b><font style="text-transform:uppercase">SILA ISIKAN MAKLUMAT DI TAB PERMOHONAN SEBELUM &nbsp;</font></b>
                </c:if>
                <c:if test="${actionBean.permohonan ne null}">
            <legend></legend>
            <div class="content" align="center">
                <table border="0" width="80%">
                    <tr><td align="center"><b>MAJLIS MESYUARAT KERAJAAN NEGERI MELAKA</b></td></tr>
                </table><br><br>  

                <table border="0" width="80%" cellspacing="10">
                    <%--
                    <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">
                         <c:if test="${edit}">
                             <c:if test="${actionBean.stageId eq 'rekodkpsnmmkncetaksurat'}">
                             <tr><td>
                                     <table border="0" width="80%" cellspacing="5">
                                         <tr><td width="15%"><b id="idLabel">PERSIDANGAN  </b></td>
                                             <td><b>:</b></td>
                                             <td><s:text name="persidangan" size="20" class="normal_text" /></td>
                                         </tr>
                                         <tr><td id="idLabel"><b>MASA </b></td>
                                             <td><b>:</b></td>
                                             <td>
                                                 <s:select name="jam" style="width:60px;" id="jam">
                                                     <s:option value="00">Jam</s:option>
                                                     <c:forEach var="i" begin="1" end="12" step="1" varStatus ="status">
                                                         <c:if test="${i lt 10}">
                                                             <s:option value="0${i}">0${i}</s:option>
                                                         </c:if>
                                                         <c:if test="${i gt 9}">
                                                             <s:option value="${i}">${i}</s:option>
                                                         </c:if>
                                                     </c:forEach>
                                                 </s:select>
                                                 <s:select name="minit" style="width:70px;" id="minit">
                                                     <s:option value="00">Minit</s:option>
                                                     <c:forEach var="i" begin="0" end="59" step="5" varStatus ="status">
                                                         <c:if test="${i lt 10}">
                                                             <s:option value="0${i}">0${i}</s:option>
                                                         </c:if>
                                                         <c:if test="${i gt 9}">
                                                             <s:option value="${i}">${i}</s:option>
                                                         </c:if>
                                                     </c:forEach>
                                                 </s:select>
                                                 <s:select name="pagiPetang" style="width:80px;" id="masa">
                                                     <s:option value="00">Pilih</s:option>
                                                     <s:option value="AM">Pagi</s:option>
                                                     <s:option value="PM">Petang</s:option>
                                                 </s:select>
                                             </td>
                                         </tr>
                                         <tr><td id="idLabel"><b>TARIKH  </b></td>
                                             <td><b>:</b></td>
                                             <td><s:text name="tarikhMesyuarat" id="datepicker" class="datepicker" size="12" /></td>
                                         </tr>
                                         <tr><td id="idLabel"><b>TEMPAT  </b></td>
                                             <td><b>:</b></td>
                                             <td><s:textarea name="tempatsidang" cols="50"/></td>
                                         </tr>
                                     </table>
                                 </td></tr>
                                </c:if>
                             </c:if>
                             <c:if test="${!edit}">                           
                             <c:if test="${actionBean.stageId eq 'rekodkpsnmmkncetaksurat' || actionBean.stageId eq 'pindaanagihan' || actionBean.stageId eq 'pindaanrisalatmmkn' || actionBean.stageId eq 'semakpindaan'}">
                             <tr><td>
                                     <table border="0" width="80%" cellspacing="5">
                                         <tr><td width="18"><b id="idLabel">PERSIDANGAN  </b></td>
                                             <td><b>:</b></td>
                                             <td>${actionBean.persidangan} </td>
                                         </tr>
                                         <tr><td id="idLabel"><b>MASA  </b></td>
                                             <td><b>:</b></td>
                                             <td>${actionBean.masasidang}</td>
                                         </tr>
                                         <tr><td id="idLabel"><b>TARIKH  </b></td>
                                             <td><b>:</b></td>
                                             <td>${actionBean.tarikhMesyuarat}</td>
                                         </tr>
                                         <tr><td id="idLabel"><b>TEMPAT  </b></td>
                                             <td><b>:</b></td>
                                             <td>${actionBean.tempatsidang} </td>
                                         </tr>
                                     </table>
                                 </td></tr>
                             </c:if>
                             </c:if>
                         </c:if>
                    --%>
                    <tr><td><b>TAJUK</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="tajuk" rows="5" cols="150" class="normal_text"/></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td><b>${actionBean.tajuk} &nbsp;</b></td></tr>
                    </c:if>
                    <tr><td><b>1. TUJUAN</b></td></tr>
                    <c:if test="${edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea rows="5" cols="150" name="tujuan" class="normal_text" /></td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp; <b>1.1</b> &nbsp;${actionBean.tujuan} &nbsp;</td></tr>
                    </c:if>
                    <tr><td><b>2. LATAR BELAKANG</b></td></tr>

                    <c:if test ="${edit}">
                        <tr><td><b>2.1 Perihal Permohonan</b></td></tr>
                    </c:if> 
                    <c:if test ="${!edit}">                        
                        <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;2.1 Perihal Permohonan</b></td></tr>
                    </c:if> 

                    <c:if test="${edit}">
                        <tr><td>
                            <table id ="dataTable1">                                    
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                    <tr><td style="display:none">${line.idKandungan}</td>
                                    <td><b><c:out value="2.1.${i}"/></b></td>
                                    <td><s:textarea name="perihalPermohonan${i}" id="perihalPermohonan${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea>                                       </td></tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>                        
                    </c:if>
                    <tr><td><s:hidden name="rowCount1" id="rowCount1"/>
                        <s:hidden name="count1" id="count1" value="${fn:length(actionBean.senaraiKandungan1)}"/>
                    </td></tr>
                    <c:if test="${actionBean.btn}">
                        <tr>
                        <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1')"/>&nbsp;
                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow1()" /></td>
                        </tr>                            
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan1}" var="line">
                                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><c:out value="2.1.${i}"/></b>&nbsp;&nbsp;</td>
                                    <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>

                    <c:if test ="${edit}">
                        <tr><td><b>2.2 Perihal Tanah</b></td></tr>
                    </c:if> 
                    <c:if test ="${!edit}">
                        <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;2.2 Perihal Tanah</b></td></tr>
                    </c:if>

                    <c:if test="${edit}">
                        <tr><td>
                            <table id ="dataTable2">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                    <tr>
                                    <td style="display:none">${line.idKandungan}</td>
                                    <td><b><c:out value="2.2.${i}"/></b></td>
                                    <td><s:textarea name="perihalTanah${i}" id="perihalTanah${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea> </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>
                    <tr><td><s:hidden name="rowCount2" id="rowCount2"/>
                        <s:hidden name="count2" id="count2" value="${fn:length(actionBean.senaraiKandungan2)}"/>
                    </td></tr>
                    <c:if test="${actionBean.btn}">
                        <tr>
                        <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow2('dataTable2')"/>&nbsp;
                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow2()" /></td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan2}" var="line">
                                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><c:out value="2.2.${i}"/></b>&nbsp;&nbsp;</td>
                                    <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>

                    <c:if test ="${edit}">
                        <tr><td><b>2.3 Perihal Pemohon</b></td></tr>
                    </c:if> 
                    <c:if test ="${!edit}">
                        <tr><td><b>&nbsp;&nbsp;&nbsp;&nbsp;2.3 Perihal Pemohon</b></td></tr>
                    </c:if>

                    <c:if test="${edit}">
                        <tr><td>
                            <table id ="dataTable3">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                    <tr>
                                    <td style="display:none">${line.idKandungan}</td>
                                    <td><b><c:out value="2.3.${i}"/></b></td>
                                    <td><s:textarea name="perihalPemohon${i}" id="perihalPemohon${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea> </td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>
                    <tr><td><s:hidden name="rowCount3" id="rowCount3"/>
                        <s:hidden name="count3" id="count3" value="${fn:length(actionBean.senaraiKandungan3)}"/>
                    </td></tr>
                    <c:if test="${actionBean.btn}">
                        <tr>
                        <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow3('dataTable3')"/>&nbsp;
                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow3()" /></td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan3}" var="line">
                                    <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <b><c:out value="2.3.${i}"/></b>&nbsp;&nbsp;</td>
                                    <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>


                    <tr><td><b>3. PERIHAL RAYUAN</b></td></tr>                    
                    <c:if test="${edit}">
                        <tr><td>
                            <table>
                                <tr>
                                <td><b>3.1</b></td>
                                <td><s:textarea name="perihalRayuan" rows="5" cols="150" id="perihalRayuan" class="normal_text"/></td>
                                </tr>
                            </table>
                        </td></tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>&nbsp;&nbsp;&nbsp;&nbsp; <b>3.1</b> &nbsp; ${actionBean.perihalRayuan}</td></tr>
                    </c:if>

                    <tr><td><b><font style="text-transform:uppercase">4. PERAKUAN PENTADBIR TANAH ${actionBean.pejTanah} </font></b></td></tr>

                    <c:if test="${edit}">
                        <tr><td>
                            <table id ="dataTable4">                                    
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                    <tr><td style="display:none">${line.idKandungan}</td>                                           
                                    <td><b><c:out value="4.${i}"/></b></td>                                                                                
                                    <td><s:textarea name="perakuanPtd${i}" id="perakuanPtd${i}"  rows="5" cols="150" class="normal_text">${line.kandungan}</s:textarea></td>
                                        </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>                        
                    </c:if>
                    <tr><td><s:hidden name="rowCount4" id="rowCount4"/>
                        <s:hidden name="count4" id="count4" value="${fn:length(actionBean.senaraiKandungan4)}"/>
                    </td></tr>
                    <c:if test="${actionBean.btn}">
                        <tr>
                        <td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <s:button class="btn" value="Tambah" name="add" onclick="addRow4('dataTable4')"/>&nbsp;
                            <s:button name="hapus" value="Hapus" class="btn" onclick="deleteRow4()" /></td>
                        </tr>
                    </c:if>
                    <c:if test="${!edit}">
                        <tr><td>
                            <table>
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.senaraiKandungan4}" var="line">
                                    <tr>                                          
                                    <td><b>&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="4.${i}"/></b></td>                                         
                                    <td>&nbsp;&nbsp; ${line.kandungan} </td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </table>
                        </td>
                        </tr>
                    </c:if>                    
                    <c:if test="${actionBean.pengguna.kodCawangan.kod eq '00'}">                                                                      

                        <tr><td><b>5. PERAKUAN PENGARAH TANAH DAN GALIAN MELAKA</b></td></tr>

                        <c:if test="${edit1}">
                            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<s:textarea name="perakuanptg" rows="5" cols="150" id="perakuanptg" class="normal_text"/></td></tr>
                        </c:if>
                        <c:if test="${!edit1}">
                            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp; <b>5.1</b> &nbsp; ${actionBean.perakuanptg} &nbsp;</td></tr>
                        </c:if>                      
                    </c:if>
                </table>

                <c:if test="${edit2}">
                    <p align="center">                        
                        <c:if test="${actionBean.stageId ne 'ptd1'}">
                            <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                        </c:if>
                        <c:if test="${actionBean.stageId eq 'ptd1'}">
                            <s:button name="kertasMMKNBaru" id="kertasBaru" value="Kertas MMKN Baru" class="longbtn" onclick="validation(this.name, this.form);"/>
                        </c:if>
                    </p>
                </c:if>
            </div>
        </c:if>
    </fieldset>
</div>
</s:form>