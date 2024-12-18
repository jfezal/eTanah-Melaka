

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

<script type="text/javascript">
    function addRow(index)
    {
        //        if(confirm('Adakah anda pasti untuk menambah data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/surat_rayuanNS?tambahRow&index='+index;

        $.get(url,
        function(data){
            $('#page_div').html(data);
        },'html');

        //        }
    }
    function menyimpan(index,i)
    {
        var kand;
        if(index == 2)
            kand = document.getElementById("kandungan"+i).value;


        //        if(confirm('Adakah anda pasti untuk simpan data ini?')) {
        var url = '${pageContext.request.contextPath}/pelupusan/surat_rayuanNS?simpan&index='+index+'&kandungan='+kand;
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
            var url = '${pageContext.request.contextPath}/pelupusan/surat_rayuanNS?deleteRow&idKandungan='+idKandungan;

            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');

        }
    }
    
    function kemaskiniRow(idKandungan,index,i){
        //        alert("hi");
        //        alert(index)
        //        alert(i)
        //alert(idKandungan);
        var kand;
        if(index == 2)
        //            alert("masuk")
            kand = document.getElementById("kandungan"+i).value;
        //            alert(kand);
    
        
        if(confirm('Adakah anda pasti untuk kemaskini data ini?')) {
            var url = '${pageContext.request.contextPath}/pelupusan/surat_rayuanNS?kemaskini&index='+index+'&kandungan='+kand+'&idKandungan='+idKandungan;


            $.get(url,
            function(data){
                $('#page_div').html(data);
            },'html');
        }
    }


</script>
<s:form beanclass="etanah.view.stripes.pelupusan.SuratRayuanNSActionBean">
    <s:messages/>
    <s:errors/>
    <div class="subtitle1">
        <fieldset class="aras1">
            <legend>Surat Rayuan</legend>
            <table>
                <tr>
                    <td>Tajuk</td>
                    <td></td>
                    <td>
                        <table>
                            <tr><td>
                                    <s:textarea name="tajuk" cols="150"  rows="5" value="${actionBean.tajuk}"/>
                                </td>
                                <td>
                                    <s:button name="kemaskiniTajuk" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>Kandungan</td>
                    <td></td>
                    <td>
                        <table>
                            <tr> 
                                <td>
                                    <s:textarea name="kandunganPertama" cols="150"  rows="5" value="${actionBean.kandunganPertama}" class="normal_text"/>
                                </td>
                                <td>
                                    <s:button name="kemaskiniKandunganPertama" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </td>
                            </tr>
                            <tr>
                                <td  > ${actionBean.bay} 
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>



                <!--            <table>
                
                                <td >
                                    <label >&nbsp;</label>
                                    <label > </label>
                                </td>
                
                <%--<tr>
                    <td>Kandungan</td>
                    <td></td>
                    <td>
                        <table>
                            <tr><td width="23" valign="top"></td>
                                <td>
                                    <s:textarea name="bay" cols="150"  rows="5" value="${actionBean.bay}" class="normal_text"/>
                                </td>
                                <td>
                                    <s:button name="kemaskinibay" value="Kemaskini" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>--%>

                <tr>
                    <td></td>
                    <td></td>
                    <td>
                        <table>-->
                <c:set var="i" value="2" />
                <c:set var="a" value="1" />
                <c:forEach items="${actionBean.mohonKertasKandTmbhn}" var="line">
                    <tr>
                        <td>&nbsp;</td>
                         <td width="23" valign="top"><c:out value="${i}"/></td>
                        <td>
                            <table>
                                 <tr>
                                   
                                    <td><s:textarea  id="kandungan${a}"name="mohonKertasKandTmbhn[${a-1}].kandungan" cols="150"  rows="5" class="normal_text"/></td>
                                    <td><s:button value="Hapus" class="btn" name="tambah" onclick="deleteRow(${line.idKandungan})"></s:button><br><s:button value="Kemaskini" class="btn" name="kemaskini" onclick="kemaskiniRow(${line.idKandungan},'2',${a})"></s:button>  </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                   <c:set var="i" value="${i+1}" />
                   <c:set var="a" value="${a+1}" />
                </c:forEach>
                <tr>
                    <td  align="middle" colspan="3">
                        <s:button value="Tambah" class="btn" name="simpan1"  onclick="addRow('2')"></s:button> <s:button name="simpan1" id="save1" value="Simpan" class="btn" onclick="menyimpan('2',${a-1})"></s:button></td>

                    </tr>
                </table>
            </fieldset>

        </div>
</s:form>