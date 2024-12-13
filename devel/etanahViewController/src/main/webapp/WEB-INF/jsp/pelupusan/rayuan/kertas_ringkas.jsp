<%-- 
    Document   : kertas_ringkas
    Created on : Jul 5, 2011, 6:49:57 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function menyimpan(index,i)
    {
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 7)
            kand = document.getElementById("kandungan3"+i).value;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }       
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?simpan&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

  
       
       
    }
    function deleteRow(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?deleteRow&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRow(index)
    {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?tambahRow&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }
    
    function kemaskiniRow(idKandungan,index,i){
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 7)
            kand = document.getElementById("kandungan3"+i).value;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        
        if(confirm('Adakah anda pasti untuk kemaskini data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?kemaskini&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    
    
    function simpanPerakuanPTG(index,i)
    {
        var kand;

        if(index==6){
            kand = document.getElementById("kandungan5"+i).value;
        }
        
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?simpanPerakuanPTG&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

       
    }
    function deleteRowPerakuanPTG(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?deleteRowPerakuanPTG&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    
    
    function kemaskiniRowPerakuanPTG(idKandungan,index,i){
        var kand;
        if(index==6){
            kand = document.getElementById("kandungan5"+i).value;
        }
        
        if(confirm('Adakah anda pasti untuk kemaskini data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?kemaskiniPerakuanPTG&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }

    function tambahPerakuanRowPTG(index)
    {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?tambahPerakuanRowPTG&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

    }
    
    function simpanPerakuanPTD(index,i)
    {
        var kand;

        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?simpanPerakuanPTD&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

  
       
       
    }
    function deleteRowPerakuanPTD(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?deleteRowPerakuanPTD&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    
    
    function kemaskiniRowPerakuanPTD(idKandungan,index,i){
        var kand;
        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        if(index==5){
            kand = document.getElementById("kandungan5"+i).value;
        }
        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        
        if(confirm('Adakah anda pasti untuk kemaskini data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?kemaskiniPerakuanPTD&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    function tambahPerakuanRowPTD(index)
    {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkas?tambahPerakuanRowPTD&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');
    }
    
    function test() {
        alert("Hi");
    }


</script>
<s:form beanclass="etanah.view.stripes.pelupusan.KertasRingkasActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" align="center">
        <fieldset class="aras1"> 
            <table align="center" width="80%">
                <tr>
                    <td>
                        <c:if test="${edit}"><font size="3"><b><s:textarea name="tajuk" id="tajuk" cols="150"  rows="3"/></b></font>
                            <s:button value="Kemaskini" class="btn" name="kemaskiniTajuk" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button></c:if>
                        <c:if test="${!edit}">
                            <font size="3"><b>${actionBean.tajuk}</b></font></c:if>
                        </td>
                    </tr>
                </table>
                <br/><br/>
                <table align="left" width="80%" border="0">
                    <tr>
                        <td width="40">1.0</td>
                        <td width="100%"><b>TUJUAN</b></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table border="0">
                            <c:set var="i" value="1" />
                            <c:if test="${edit}">
                                <tr>
                                    <td width="23" valign="top"><c:out value="1.${i}"/></td>
                                    <td><s:textarea name="tujuan" id="tujuan" cols="150"  rows="5" class="normal_text"/></td>
                                    <td><s:button value="Kemaskini" class="btn" name="kemaskiniTujuan" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button> </td>
                                    </tr>
                            </c:if>
                            <c:if test="${!edit}">

                                <tr>
                                    <td width="23" valign="top"><c:out value="1.${i}"/></td>
                                    <td>${actionBean.tujuan}</td>
                                </tr>
                            </c:if>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="40">2.0</td>
                    <td width="657"><b>LATAR BELAKANG</b></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table border="0">
                            <c:if test="${edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.latarBelakangList}" var="line">
                                    <tr>
                                        <td width="23" valign="top"><c:out value="2.${i}"/></td>
                                        <td><s:textarea  id="kandungan2${i}"name="latarBelakangList[${i-1}].kandungan" cols="150"  rows="5" class="normal_text"/></td>
                                        <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'2',${i})"></s:button> </td>
                                    </tr><c:set var="i" value="${i+1}" />
                                </c:forEach>

                                <tr>
                                    <td width="23" valign="top"></td>
                                    <td  align="left">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1})"></s:button>
                                        </td>
                                    </tr>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.latarBelakangList}" var="line">
                                    <tr>
                                        <td width="23" valign="top"><c:out value="2.${i}"/></td>
                                        <td>${line.kandungan}</td>
                                    </tr>
                                    <c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </c:if>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="40">3.0</td>
                    <td width="657"><b>RAYUAN</b>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table border="0">
                            <c:if test="${edit}">
                              
                                <tr>
                                    <td width="23" valign="top"><c:out value="3.1"/></td>
                                    <td><s:textarea name="perihalRayuan" id="perihalRayuan" cols="150"  rows="5" class="normal_text"/></td>
                                    <td><s:button value="Kemaskini" class="btn" name="kemaskiniRayuan" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button> </td>
                                    </tr>
                            </c:if>
                                    <c:if test="${!edit}">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="3.1"/></td>
                                            <td>${actionBean.perihalRayuan}</td>
                                        </tr>
                                    </c:if>
                                   <c:if test="${edit}">     
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td>
                                            <table border="0">         
                                                <c:set var="i" value="1" />
                                                <c:forEach items="${actionBean.rayuanList}" var="line">
                                                    <tr>
                                                        <td width="23" valign="top"><c:out value="3.1.${i}"/></td>
                                                        <td><s:textarea  id="kandungan3${i}"name="rayuanList[${i-1}].kandungan" cols="129"  rows="5" class="normal_text"/></td>
                                                        <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'7',${i})"></s:button> </td>
                                                        </tr>
                                                    <c:set var="i" value="${i+1}" />
                                                </c:forEach>
                                                    
                                                <tr>
                                                    <td width="23" valign="top"></td>
                                                    <td  align="left">
                                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('7')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('7',${i-1})"></s:button>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                            </c:if>
                            <c:if test="${!edit}">
                                
                                <tr>
                                    <td>&nbsp;</td>
                                    <td>
                                        <table border="0">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.rayuanList}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="3.1.${i}"/></td>
                                                    <td>${line.kandungan}</td>
                                                        
                                                </tr>
                                                <c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </table>
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                    </td>
                </tr>
                <tr><td width="40">4.0</td>
                    <td width="657"><b>PERAKUAN PENTADBIR TANAH</b></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table border="0">
                            <c:if test="${!edit}">
                                <c:if test="${keputusanPTD}">
                                    <c:set var="t" value="1" />
                                    <c:forEach items="${actionBean.listKertasPerakuanPTD}" var="line">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${t}"/></td>
                                            <td><s:textarea  id="kandungan4${t}"name="listKertasPerakuanPTD[${t-1}].kandungan" cols="150"  rows="5" class="normal_text"/></td>
                                            <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPerakuanPTD(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPerakuanPTD(${line.idKandungan},'4',${t})"></s:button> </td>
                                            </tr>
                                        <c:set var="t" value="${t+1}" />
                                    </c:forEach>

                                    <tr>
                                        <td width="23" valign="top"></td>
                                        <td  align="left">
                                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="tambahPerakuanRowPTD('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="simpanPerakuanPTD('4',${t-1})"></s:button>
                                            </td>
                                        </tr>
                                </c:if>   
                            </c:if>
                            <c:if test="${edit}">
                                <c:if test="${keputusanPTD}">
                                    <c:set var="t" value="1" />
                                    <c:forEach items="${actionBean.listKertasPerakuanPTD}" var="line">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${t}"/></td>
                                            <td><s:textarea  id="kandungan4${t}"name="listKertasPerakuanPTD[${t-1}].kandungan" cols="150"  rows="5" class="normal_text"/></td>
                                            <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'4',${t})"></s:button> </td>
                                            </tr>
                                        <c:set var="t" value="${t+1}" />
                                    </c:forEach>

                                    <tr>
                                        <td width="23" valign="top"></td>
                                        <td  align="left">
                                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${t-1})"></s:button>
                                            </td>
                                        </tr>
                                </c:if>   
                            </c:if>
                            <c:if test="${!edit}">
                                <c:if test="${!keputusanPTD}">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.listKertasPerakuanPTD}" var="line">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                            <td>${line.kandungan}</td>

                                        </tr><c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </c:if>
                            </c:if>
                        </table></td>
                </tr>
                <c:if test="${ptg}">
                    <tr><td width="40">5.0</td>
                        <td width="657"><b>PERAKUAN PENGARAH TANAH DAN GALIAN</b></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table border="0">
                                <c:if test="${!view}">
                                    <c:if test="${keputusan}">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="5.1"/></td>
                                            <td><s:textarea name="perakuanPTG" id="perakuanPTG" cols="150"  rows="5" class="normal_text"/></td>
                                            <td><s:button value="Kemaskini" class="btn" name="kemaskiniPTG" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button> </td>
                                            </tr>
                                    </c:if>
                                </c:if>
                                <c:if test="${view}">
                                    <c:if test="${!keputusan}">
                                        <tr>
                                            <td width="23" valign="top"><c:out value="5.1"/></td>
                                            <td>${actionBean.perakuanPTG}</td>
                                        </tr>
                                    </c:if>
                                </c:if>
                                <c:if test="${!view}">
                                    <c:if test="${keputusan}">
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>
                                                <table border="0">         
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">
                                                        <tr>
                                                            <td width="23" valign="top"><c:out value="5.1.${i}"/></td>
                                                            <td><s:textarea  id="kandungan5${i}"name="listKertasPerakuanPTG[${i-1}].kandungan" cols="129"  rows="3" class="normal_text"/></td>
                                                            <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPerakuanPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPerakuanPTG(${line.idKandungan},'6',${i})"></s:button> </td>
                                                        </tr><c:set var="i" value="${i+1}" />
                                                    </c:forEach>

                                                    <tr>
                                                        <td width="23" valign="top"></td>
                                                        <td  align="left">
                                                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="tambahPerakuanRowPTG('6')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="simpanPerakuanPTG('6',${i-1})"></s:button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                    </c:if>
                                </c:if>

                                <c:if test="${view}">
                                    <c:if test="${!keputusan}">
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td>
                                                <table border="0">
                                                    <c:set var="i" value="1" />
                                                    <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">
                                                        <tr>
                                                            <td width="23" valign="top"><c:out value="5.1.${i}"/></td>
                                                            <td>${line.kandungan}</td>

                                                        </tr><c:set var="i" value="${i+1}" />
                                                    </c:forEach>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:if>
                                        <%--<tr>
                                            <td width="23" valign="top"><c:out value="5.2"/></td>
                                            <td>Diangkat untuk pertimbangan Majlis.</td>
                                            
                                        </tr>--%>
                            </table></td>
                    </tr>
                </c:if>
            </table>                    
        </fieldset> 
    </div>
</s:form>
