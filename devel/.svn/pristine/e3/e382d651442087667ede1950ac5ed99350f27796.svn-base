<%-- 
    Document   : senarai_semak_kertas_siasatan
    Created on : Feb 8, 2011, 11:22:57 AM
    Author     : Murali
    Modify by  : Siti Fariza Hanim 28022011
--%>

<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

    $(document).ready(function() {
        document.getElementById("pilihAll").checked = false;
        var table = document.getElementById("tbl");
        var rowCount = table.rows.length;
        for(var i=0; i<rowCount-1; i++) {
            var k = $("#status"+i).val();
            if($("#status"+i).val() == ""){
                document.getElementById("pilih"+i).checked = false;
                document.getElementById("status"+i).value = "T";
            }else if($("#status"+i).val() != ""){
                if($("#status"+i).val() == "Y"){
                    document.getElementById("pilih"+i).checked = true;
                }else if($("#status"+i).val() == "T"){
                    document.getElementById("pilih"+i).checked = false;
                }
            }
        }
    });


    function  changeValue(i){
        var obj = document.getElementById("pilih"+i);
        if(obj.checked == true){
    <%--alert(obj);--%>
                document.getElementById("status"+i).value = "Y";
            }else{
                document.getElementById("status"+i).value = "T";
            }

        }
        function selectall() {
            try {
                     var table = document.getElementById("tbl");
    <%--alert("table:"+table);--%>
                     var rowCount = table.rows.length;
    <%--alert("rowCount:"+rowCount);  --%>
                var chkbox1 = table.rows[0].cells[0].childNodes[0];
                if(chkbox1!=null && chkbox1.checked == true){ 
                          for(var i=0; i<rowCount-1; i++) {
                        document.getElementById("pilih"+i).checked = true;
                        document.getElementById("status"+i).value = "Y";
                    }
                }else{
                    for(var i=0; i<rowCount-1; i++) {
                        document.getElementById("pilih"+i).checked = false;
                        document.getElementById("status"+i).value = "T";
                    }
                }

            }catch(e){
                alert(e);
            }
        }
</script>
<s:form beanclass="etanah.view.penguatkuasaan.SenaraiKertasSiasatanActionBean">

    <s:messages/>
    <s:errors/>

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Senarai Semak Kertas Siasatan</legend>
            <br><br>
            <c:if test="${view}">
                <div class="content" align="left">
                    <table id="tbl" class="tablecloth">
                        <tr>
                            <th align="center"><s:checkbox name="pilihAll" id="pilihAll" onclick="selectall()" disabled="true"/>&nbsp;<b>Pilih</b></th>
                            <th align="center"><b>Bil</b></th>
                            <th align="center"><b>Perkara</b></th>
                            <th align="center"><b>Catatan</b></th>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiSemakDokumen}" var="line">
                            <tr style="font-weight:bold">
                                <td><center><s:checkbox name="pilih${i}" id="pilih${i}" onclick="changeValue(${i})" disabled="true"/>
                                    <s:hidden name="status${i}" id="status${i}" value="${line.adaDokumen}" />
                                    </center> </td>
                                <td><center><c:out value="${i+1}"/></center></td>
                                <td>${line.kodDokumen.nama}</td>
                                <td><center><s:textarea name="catatan${i}" id="catatan${i}" value="${line.catatan}" rows="4" cols="50" readonly="true"/></center>
                                </td>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </table>
                </div>
            </c:if>

            <c:if test="${edit}">
                <div class="content" align="left">
                    <table id="tbl" class="tablecloth">
                        <tr>
                            <th align="center"><s:checkbox name="pilihAll" id="pilihAll" onclick="selectall()" />&nbsp;<b>Pilih</b></th>
                            <th align="center"><b>Bil</b></th>
                            <th align="center"><b>Perkara</b></th>
                            <th align="center"><b>Catatan</b></th>
                        </tr>
                        <c:set value="0" var="i"/>
                        <c:forEach items="${actionBean.senaraiSemakDokumen}" var="line">
                            <tr style="font-weight:bold">

                                <td><center><s:checkbox name="pilih${i}" id="pilih${i}" onclick="changeValue(${i})" />
                                    <s:hidden name="status${i}" id="status${i}" value="${line.adaDokumen}" />
                                    </center> </td>
                                <td><center><c:out value="${i+1}"/></center></td>
                                <%--<td>${line.kodDokumen.nama}</td>--%>
                                <td>${line.kodDokumen.nama}</td>
                                <td><center><s:textarea name="catatan${i}" id="catatan${i}" value="${line.catatan}" rows="4" cols="50" /></center>
                                </td>
                                <%--<s:hidden name="idRujukan${i}" id="idRujukan${i}" value="${line.idRujukan}" />--%>
                            </tr>
                            <c:set var="i" value="${i+1}" />
                        </c:forEach>
                    </table>
                </div>
                <br>
                <p align="left">
                    <s:button name="simpan" id="save" value="Simpan" class="btn" onclick="doSubmit(this.form, this.name, 'page_div')"/>
                </p>
            </c:if>
        </fieldset>
    </div>
</s:form>