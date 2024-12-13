<%-- 
    Document   : kertas_ringkas_ns
    Created on : Jul 13, 2011, 3:27:15 PM
    Author     : afham
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<script type="text/javascript">

    function menyimpan(index,i)
    {
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan2"+i).value;
        if(index == 3)
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
        
        //        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?simpan&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
  
       
       
    }
    function deleteRow(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?deleteRow&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRow(index)
    {
        //        alert(2);
        //        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?tambahRow&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
    }
    
    function kemaskiniRow(idKandungan,index,i){
        //        alert("hi");
        //        alert(index)
        //        alert(i)
        //alert(idKandungan);
        var kand;
        if(index == 2)
        //            alert("masuk")
            kand = document.getElementById("kandungan2"+i).value;
        //            alert(kand);
        if(index == 3)
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
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?kemaskini&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    
      
    
    
    
    
    function menyimpanPTG(index,i)
    {
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
        
        //        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?simpanPTG&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
  
       
       
    }
    
    function kemaskiniRowPTG(idKandungan,index,i){
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
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?kemaskiniPTG&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    function deleteRowPTG(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?deleteRowPTG&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRowPTG(index)
    {
        //        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?tambahRowPTG&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
    }
    
    function menyimpanKeputusanPTG(index,i)
    {
        var kand;

        if(index==6){
            kand = document.getElementById("kandungan6"+i).value;
        }
        
        //        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?simpanKeputusanPTG&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
  
       
       
    }
    function kemaskiniRowKeputusanPTG(idKandungan,index,i){
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
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?kemaskiniKeputusanPTG&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    
    function deleteRowKeputusanPTG(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?deleteRowKeputusanPTG&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRowKeputusanPTG(index)
    {
        //        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?tambahKeputusanRowPTG&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
    }
    
    function menyimpanKeputusanPTD(index,i)
    {
        var kand;

        if(index==4){
            kand = document.getElementById("kandungan4"+i).value;
        }
        
        //        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?simpanKeputusanPTD&index='+index+'&kandungan='+kand;
            +index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
  
       
       
    }
    function deleteRowKeputusanPTD(idKandungan)
    {
        if(confirm('Adakah anda pasti untuk menghapus data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?deleteRowKeputusanPTD&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }

    function addRowKeputusanPTD(index)
    {
        //        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?tambahKeputusanRowPTD&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
    }
    
    function kemaskiniRowKeputusanPTD(idKandungan,index,i){
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
            var url = '${pageContext.request.contextPath}/pelupusan/kertas_ringkasNS?kemaskiniKeputusanPTD&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }
    


</script>
<s:form beanclass="etanah.view.stripes.pelupusan.KertasRingkasActionBeanNS">
    <s:messages/>
    <s:errors/>
    <div class="subtitle" align="center">
        <fieldset class="aras1"> 
            <table align="center" width="80%">
                <tr>
                    <td>
                        <c:if test="${edit}"><font size="3"><b><s:textarea name="tajuk" id="tajuk" cols="120"  rows="3"/></b></font>
                            <s:button value="Kemaskini" class="btn" name="kemaskiniTajuk" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button></c:if>
                        <c:if test="${!edit}">
                            <font size="3"><b>
                                ${actionBean.tajuk}</b></font></c:if>
                        </td>
                    </tr>
                </table>
                <br>
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
                                    <td><s:textarea name="tujuan" id="tujuan" cols="120"  rows="5" class="normal_text"/></td>
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
                    <td>
                        <!--                        <table border="0">
                        <c:if test="${edit}">
                            <tr>
                                <td width="23" valign="top"><c:out value="2.1"/></td>
                                <td><s:textarea name="latarBelakang" id="latarBelakang" cols="120"  rows="5" class="normal_text"/></td>
                                <td><s:button value="Kemaskini" class="btn" name="kemaskiniLatarBelakang" onclick="doSubmit(this.form, this.name, 'page_div')"></s:button> </td>
                            </tr>
                        </c:if>
                        <c:if test="${!edit}">
                           <tr>
                               <td width="23" valign="top"><c:out value="2.1"/></td>
                               <td colspan="2">${actionBean.latarBelakang}</td>
                               
                           </tr>
                        </c:if>
                        <tr>
                            <td colspan="3">&nbsp;</td>
                        </tr>
                    </table>-->
                    </td>                    
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table border="0">
                            <c:if test="${edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.latarBelakangList}" var="line">


                                    <tr>
                                        <td width="23" valign="top"><c:out value="2.${i}"/></td>
                                        <td><s:textarea  id="kandungan2${i}"name="latarBelakangList[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                        <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'2',${i})"></s:button> </td>
                                    </tr><c:set var="i" value="${i+1}" />
                                </c:forEach>

                                <tr>
                                    <td width="23" valign="top"></td>
                                    <td  align="left">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${i-1})"></s:button>
                                    <td></td>
                                </tr>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.latarBelakangList}" var="line">


                                    <tr>
                                        <td width="23" valign="top"><c:out value="2.${i}"/></td>
                                        <td>${line.kandungan}</td>

                                    </tr><c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </c:if>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>
                        <table border="0">
                            <tr>
                                <td><label>Luas :</label></td>
                                <td>Lebih kurang ${actionBean.hakmilikPermohonan.luasTerlibat} ${actionBean.hakmilikPermohonan.kodUnitLuas.nama}</td>
                            </tr>
                            <tr>
                                <td><label>Mukim :</label></td>                                
                                <td>${actionBean.hakmilikPermohonan.bandarPekanMukimBaru.nama}</td>
                            </tr>
                            <tr>
                                <td><label>Tempoh :</label></td>
                                <td>Pajakan ${actionBean.hakmilikPermohonan.noPajakan} tahun</td>
                            </tr>
                            <tr>
                                <td><label>Kategori :</label></td>
                                <td>${actionBean.hakmilikPermohonan.kategoriTanahBaru.nama}</td>
                            </tr>
                            <tr>
                                <td><label>Jenis Hakmilik Sementara :</label></td>
                                <td>${actionBean.hakmilikPermohonan.kodHakmilikSementara.nama}</td>
                            </tr>
                            <tr>
                                <td><label>Jenis Hakmilik Tetap :</label></td>
                                <td>${actionBean.hakmilikPermohonan.kodHakmilikTetap.nama}</td>
                            </tr>
                            <tr>
                                <td><label>Kadar Premium :</label></td>
                                <td>RM <s:format formatPattern="###,###,###.00" value="${actionBean.hakmilikPermohonan.kadarPremium}"/></td>
                            </tr>
                            <tr>
                                <td><label>Kadar Cukai :</label></td>
                                <td>${actionBean.hakmilikPermohonan.cukaiBaru}</td>
                            </tr>
                            <tr>
                                <td><label>Bayaran Ukur/Batu Sempadan :</label></td>
                                <td>Mengikut Jadual.</td>
                            </tr>
                            <tr>
                                <td><label>Bayaran Pendaftaran Hakmilik Sementara/Tetap :</label></td>
                                <td>Mengikut Kaedah-Kaedah Tanah Negeri.</td>
                            </tr>
                            <tr>
                                <td><label>Syarat Nyata :</label></td>
                                <td>${actionBean.hakmilikPermohonan.syaratNyataBaru.syarat}</td>
                            </tr>
                            <tr>
                                <td><label>Sekatan Kepentingan :</label></td>
                                <td>${actionBean.hakmilikPermohonan.sekatanKepentinganBaru.sekatan}</td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td width="40">3.0</td>
                    <td width="657"><b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}">HURAIAN PENOLONG PENTADBIR TANAH</c:if></b>
                        <b><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPTG' || actionBean.permohonan.kodUrusan.kod eq 'RAYK' || actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">HURAIAN PENTADBIR TANAH</c:if></b>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><table border="0">
                            <c:if test="${edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.listKertasHuraianPTD}" var="line">


                                    <tr>
                                        <td width="23" valign="top"><c:out value="3.${i}"/></td>
                                        <td><s:textarea  id="kandungan3${i}"name="listKertasHuraianPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                        <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'3',${i})"></s:button> </td>
                                    </tr><c:set var="i" value="${i+1}" />
                                </c:forEach>

                                <tr>
                                    <td width="23" valign="top"></td>
                                    <td  align="left">
                                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('3')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('3',${i-1})"></s:button>
                                    <td></td>
                                </tr>
                            </c:if>
                            <c:if test="${!edit}">
                                <c:set var="i" value="1" />
                                <c:forEach items="${actionBean.listKertasHuraianPTD}" var="line">


                                    <tr>
                                        <td width="23" valign="top"><c:out value="3.${i}"/></td>
                                        <td>${line.kandungan}</td>

                                    </tr><c:set var="i" value="${i+1}" />
                                </c:forEach>
                            </c:if>
                        </table>
                    </td>
                </tr>


                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}">
                    <tr><td>4.0</td>
                        <td>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}"><b>SYOR PENTADBIR TANAH</b></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table border="0">
                                <c:if test="${edit}">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">


                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                            <td><s:textarea  id="kandungan4${i}"name="listKertasSyorPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                            <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'3',${i})"></s:button> </td>
                                        </tr><c:set var="i" value="${i+1}" />
                                    </c:forEach>

                                    <tr>
                                        <td width="23" valign="top"></td>
                                        <td  align="left">
                                            <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1})"></s:button>
                                        <td></td>
                                    </tr>
                                </c:if>
                                <c:if test="${!edit}">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">


                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                            <td>${line.kandungan}</td>

                                        </tr><c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </c:if>
                            </table>
                        </td>
                    </tr>

                    <tr><td>5.0</td>
                        <td>
                            <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}"><b>KEPUTUSAN</b></c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table border="0">
                                <tr>
                                    <td>5.1</td>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod ne 'RAYL'}">
                                        <td>Dibentangkan untuk pertimbangan dan keputusan Pengarah Tanah dan Galian, Negeri Sembilan.</td>
                                    </c:if>
                                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}">
                                        <td>Dibentangkan untuk pertimbangan dan keputusan Pentadbir Tanah Daerah, Negeri Sembilan.</td>
                                    </c:if>

                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:if>


                <c:if test="${actionBean.stageId ne '01KemasukanKertasRingkas' and actionBean.stageId ne '02SemakKertas'}">
                    <!--<c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}">
                       <tr><td>4.0</td>
                           <td><c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RAYL'}"><b>KEPUTUSAN PENTADBIR TANAH</b></c:if>
                           </td>
                       </tr>
                       <tr>
                           <td>&nbsp;</td>
                           <td><table border="0">
                                   <tr>
                                       <td>Keputusan PTD :</td>
                                       <td>${actionBean.mohonFasa.keputusan.nama}</td>
                                   </tr>
                                   <tr>
                                       <td>Catatan :</td>
                                       <td>${actionBean.mohonFasa.ulasan}</td>
                                   </tr>   
                               </table>
                           </td>
                       </tr>
                    </c:if> -->



                    <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}">
                        <tr><td>4.0</td>
                            <td>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}"><b>SYOR PENTADBIR TANAH</b></c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${edit}">
                                        <c:if test="${!keputusanPTD}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td><s:textarea  id="kandungan4${i}"name="listKertasSyorPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'4',${i})"></s:button> </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('4',${i-1})"></s:button>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${!edit}">
                                        <c:if test="${!keputusanPTD}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!edit}">
                                        <c:if test="${keputusanPTD}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td><s:textarea  id="kandungan4${i}"name="listKertasSyorPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowKeputusanPTD(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowKeputusanPTD(${line.idKandungan},'4',${i})"></s:button> </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowKeputusanPTD('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanKeputusanPTD('4',${i-1})"></s:button>
                                                </td>
                                            </tr>
                                        </c:if>   
                                    </c:if>
                                </table></td>
                        </tr>

                        <tr><td>5.0</td>
                            <td>
                                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}"><b>PENUTUP</b></c:if>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <tr>
                                        <td>5.1</td>
                                        <td>Dikemukakan untuk dipertimbangkan serta keputusan Pengarah Tanah dan Galian, Negeri Sembilan.</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                    </c:if>
                </c:if>

                <%-- untuk premium NS PTG--%>
                <%-- <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">
                    <c:if test="${ptg}">
                        <tr><td>4.0</td>
                            <td><b>HURAIAN PENGARAH TANAH DAN GALIAN 2</b>
                            </td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td><s:textarea  id="kandungan4${i}"name="listKertasSyorPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPTG(${line.idKandungan},'4',${i})"></s:button> </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowPTG('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanPTG('4',${i-1})"></s:button>
                                                    <td></td>
                                                </tr>
                                        </c:if>
                                    </c:if>

                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                                <tr>
                                                    <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                </table></td>
                        </tr>
                        <tr><td>5.0</td>
                            <td><b>SYOR PENGARAH TANAH DAN GALIAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td><s:textarea  id="kandungan5${i}"name="listKertasPerakuanPTG[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPTG(${line.idKandungan},'5',${i})"></s:button>  </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowPTG('5')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanPTG('5',${i-1})"></s:button>
                                                    <td></td>
                                                </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>           
                                </table></td>
                        </tr>
                        <tr><td>6.0</td>
                            <td><b>KEPUTUSAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <tr>
                                                <td width="23" valign="top">6.1</td>
                                                <td>Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus</td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </table></td>
                        </tr>
                    </c:if>
                </c:if> --%>

                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RYKN'}">

                    <tr><td>4.0</td>
                        <td><b>SYOR PENTADBIR TANAH</b>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td><table border="0">
                                <c:if test="${!view}">
                                    <c:if test="${!keputusan and !ptg}">
                                        <c:set var="i" value="1" />
                                        <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">
                                            <tr>
                                                <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                                <td><s:textarea  id="kandungan4${i}"name="listKertasSyorPTD[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'4',${i})"></s:button> </td>
                                            </tr><c:set var="i" value="${i+1}" />
                                        </c:forEach>

                                        <tr>
                                            <td width="23" valign="top"></td>
                                            <td  align="left">
                                                <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowKeputusanPTD('4')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanKeputusanPTD('4',${i-1})"></s:button>
                                            <td></td>
                                        </tr>
                                    </c:if>
                                </c:if>

                                <c:if test="${ptg}">
                                    <c:set var="i" value="1" />
                                    <c:forEach items="${actionBean.listKertasSyorPTD}" var="line">


                                        <tr>
                                            <td width="23" valign="top"><c:out value="4.${i}"/></td>
                                            <td>${line.kandungan}</td>

                                        </tr><c:set var="i" value="${i+1}" />
                                    </c:forEach>
                                </c:if>



                            </table></td>
                    </tr>

                    <c:if test="${ptg}">

                        <tr><td>5.0</td>
                            <td><b>HURAIAN PENGARAH TANAH DAN GALIAN NEGERI SEMBILAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td><s:textarea  id="kandungan5${i}"name="listKertasPerakuanPTG[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPTG(${line.idKandungan},'5',${i})"></s:button>  </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowPTG('5')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanPTG('5',${i-1})"></s:button>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>           
                                </table></td>
                        </tr>

                        <tr><td>6.0</td>
                            <td><b>SYOR PENGARAH TANAH DAN GALIAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasKeputusanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="6.${i}"/></td>
                                                    <td><s:textarea  id="kandungan6${i}"name="listKertasKeputusanPTG[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPTG(${line.idKandungan},'6',${i})"></s:button>  </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowPTG('6')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanPTG('6',${i-1})"></s:button>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasKeputusanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="6.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasKeputusanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="6.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>           
                                </table></td>
                        </tr>
                        <tr><td>7.0</td>
                            <td><b>KEPUTUSAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <c:if test="${ptg}">

                                        <tr>
                                            <td width="23" valign="top">7.1</td>
                                            <td>Dibentangkan untuk pertimbangan dan keputusan Majlis Mesyuarat Kerajaan Negeri Sembilan Darul Khusus.</td>
                                        </tr>

                                    </c:if>
                                </table></td>
                        </tr>
                    </c:if>
                </c:if>



                <%-- untuk rayuan lanjutan NS PTG--%>
                <c:if test="${actionBean.permohonan.kodUrusan.kod eq 'RLPTG'}">
                    <c:if test="${ptg}">
                        <c:if test="${keputusan}">
                            <tr><td>6.0</td>
                                <td><b>KEPUTUSAN PENGARAH TANAH DAN GALIAN</b></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                                <td><table border="0">
                                        <tr>
                                            <td>Keputusan PTG :</td>
                                            <td>${actionBean.mohonFasa.keputusan.nama}</td>
                                        </tr>
                                        <tr>
                                            <td>Catatan :</td>
                                            <td>${actionBean.mohonFasa.ulasan}</td>
                                        </tr>
                                    </table></td>
                            </tr>
                        </c:if>
                        <%--
                        <tr><td>6.0</td>
                                <td><b>HURAIAN PENGARAH TANAH DAN GALIAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table  border="0">
                                    <c:if test="${!view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td><s:textarea  id="kandungan5${i}"name="listKertasPerakuanPTG[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowPTG(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowPTG(${line.idKandungan},'5',${i})"></s:button>  </td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowPTG('5')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanPTG('5',${i-1})"></s:button>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasPerakuanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="5.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>           
                                </table></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr><td>7.0</td>
                            <td><b>KEPUTUSAN PENGARAH TANAH DAN GALIAN</b></td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td><table border="0">
                                    <tr>
                                        <td>Keputusan PTG :</td>
                                        <td>${actionBean.mohonFasa.keputusan.nama}</td>
                                    </tr>
                                    <tr>
                                        <td>Catatan :</td>
                                        <td>${actionBean.mohonFasa.ulasan}</td>
                                    </tr>
                                    
                                    <c:if test="${!view}">
                                        <c:if test="${keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasKeputusanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="6.${i}"/></td>
                                                    <td><s:textarea  id="kandungan6${i}"name="listKertasKeputusanPTG[${i-1}].kandungan" cols="120"  rows="5" class="normal_text"/></td>
                                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRowKeputusanPTG(${line.idKandungan})"></s:button> <br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRowKeputusanPTG(${line.idKandungan},'6',${i})"></s:button></td>
                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>

                                            <tr>
                                                <td width="23" valign="top"></td>
                                                <td  align="left">
                                                    <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRowKeputusanPTG('6')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpanKeputusanPTG('6',${i-1})"></s:button>
                                                <td></td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${view}">
                                        <c:if test="${!keputusan}">
                                            <c:set var="i" value="1" />
                                            <c:forEach items="${actionBean.listKertasKeputusanPTG}" var="line">


                                                <tr>
                                                    <td width="23" valign="top"><c:out value="6.${i}"/></td>
                                                    <td>${line.kandungan}</td>

                                                </tr><c:set var="i" value="${i+1}" />
                                            </c:forEach>
                                        </c:if>
                                    </c:if>
                                </table></td>
                        </tr>--%>

                    </c:if>
                </c:if>
            </table>                    
        </fieldset> </div>
    </s:form>


