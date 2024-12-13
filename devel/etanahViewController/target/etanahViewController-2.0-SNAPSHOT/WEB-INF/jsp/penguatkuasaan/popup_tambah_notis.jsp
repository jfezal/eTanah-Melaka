<%-- 
    Document   : popup_tambah_surat
    Created on : Nov 11, 2011, 11:24:21 AM
    Author     : latifah.iskak
--%>


<%@ page contentType="text/html;charset=windows-1252"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/tablecloth.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/styles.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/eTanahSkin.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui-lightness/jquery-ui-1.7.2.custom.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/datepicker.css"/>
<link type="text/css" rel="stylesheet"
      href="<%= request.getContextPath()%>/pub/styles/ui.theme.css"/>
<script type="text/javascript"
src="<%= request.getContextPath()%>/pub/scripts/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.alphanumeric.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/etanah-script.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/ui.datepicker.js"></script>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<style type="text/css">
    #rujukan {
        float:right;
        margin-right:11.0em;
    }

    #alamat {
        float:left;
        margin-left:-27.0em;
    }

    #kandungan {
        float:left;
        margin-left:-12em;
        margin-top: 168px;
    }

    #up {
        float:left;
        margin-left:26.1em;
    }

    #faks {
        float:right;
        margin-left: 137px;
        margin-right: 40px;
        /*margin-top: -24px;*/
    }

    #viewFaks {
        float:right;
        margin-top: -19px;
        margin-left: 572px;
    }

    #oleh {
        float:left;
        margin-left:-16em;
        /*        margin-top: 800px;*/
    }



</style>
<script type="text/javascript">
    $(document).ready( function(){
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    });

    function save(event, f){

        var q = $(f).formSerialize();
        var url = f.action + '?' + event;
        $.post(url,q,
        function(data){
            $('#page_div',opener.document).html(data);
            self.opener.refreshPageNotis();
            self.close();
        },'html');

    }
    
    function validateForm(){
        if($('#tajuk').val()=="")
        {
            alert('Sila masukkan tajuk terlebih dahulu');
            $('#tajuk').focus();
            return false;
        }
        
        if($('#makluman').val()=="")
        {
            alert('Sila masukkan maklumat terlebih dahulu');
            $('#makluman').focus();
            return false;
        }
        
        if($('#kandunganAwalan').val()=="")
        {
            alert('Sila masukkan maklumat terlebih dahulu');
            $('#kandunganAwalan').focus();
            return false;
        }
        
        
        return true;
    }

    function addRow1(tableID,k) {

        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;
        var row = table.insertRow(rowCount);

        var cell0 = row.insertCell(0);
        cell0.innerHTML = "<em>*</em><b>"+(rowCount+k+1)+".</b>";

        var cell1 = row.insertCell(1);
        var element1 = document.createElement("textarea");
        element1.t = "kandungan"+(rowCount+1);
        element1.rows = 5;
        element1.cols = 100;
        element1.name ="kandungan"+(rowCount+1);
        element1.id ="kandungan"+(rowCount+1);
        cell1.appendChild(element1);

        document.getElementById("rowCount").value=rowCount+1;
        var cell2 = row.insertCell(2);
    <%-- var e =document.createTextNode(' ');--%>
            var e1 = document.createElement("INPUT");
            e1.t = "button"+(rowCount+1);
            e1.setAttribute("type","button");
            e1.className="btn";
            e1.value="Hapus";
            e1.id =(rowCount+1);
            e1.onclick=function(){deleteRow(tableID,(e1.id));};
    <%--cell2.appendChild(e);--%>
            cell2.appendChild(e1);

            var cell3 = row.insertCell(3);
            var element2 = document.createElement("input");
            element2.setAttribute("name","idKand"+(rowCount+1));
            element2.setAttribute("id","idKand"+(rowCount+1));
            element2.setAttribute("value","noID");
            element2.setAttribute("type","hidden");

            var element3 = document.createElement("br");
            cell3.appendChild(element2);
            cell3.appendChild(element3);
        }

        function deleteRow(tableID,elementId)
        {
            //alert(elementId);
            if(confirm('Adakah anda pasti untuk hapus?')) {
                var rowId = "idKand"+elementId;
                var id =  document.getElementById(rowId).value;
                if(id != "noID"){
                    var url = '${pageContext.request.contextPath}/penguatkuasaan/notis_kosongkan_tanah?deleteUlasan&idKandungan=' +id;
                    $.get(url,
                    function(data){
                        alert(data);
                        $("#tableNotisDiv").replaceWith($('#tableNotisDiv', $(data)));
                        //                        $('#page_div').html(data);
                    },'html');
                }else{
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    document.getElementById(tableID).deleteRow(elementId-1);
                    document.getElementById("rowCount").value =rowCount-1;

                }
            }

            regenerateBill(tableID);
        }

        function regenerateBill(tableID){
            try{

                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
    <%--alert("rowcount"+rowCount);--%>
                if(rowCount > 0){
                    for(var i=0;i<rowCount;i++){
                        var a = table.rows[i].cells[0];
                        a.innerHTML= "<em>*</em><b>"+(i+3)+".</b>";
                        table.rows[i].cells[1].childNodes[0].name= 'kandungan'+(i+1);
                        table.rows[i].cells[1].childNodes[0].id= 'kandungan'+(i+1);
                        table.rows[i].cells[2].childNodes[0].id= i+1;
                        table.rows[i].cells[3].childNodes[0].id= "idKand"+(i+1);
                    }
                }
            }catch(e){
                alert(e);
                alert("Error in regenerateBill");
            }
        }

        function test(f) {
            $(f).clearForm();
        }


        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }


        function validateNumber(elmnt,content) {
            //if it is character, then remove it..
            if (isNaN(content)) {
                elmnt.value = removeNonNumeric(content);
                return;
            }
        }

        function removeNonNumeric( strString )
        {
            var strValidCharacters = "1234567890";
            var strReturn = "";
            var strBuffer = "";
            var intIndex = 0;
            // Loop through the string
            for( intIndex = 0; intIndex < strString.length; intIndex++ )
            {
                strBuffer = strString.substr( intIndex, 1 );
                // Is this a number
                if( strValidCharacters.indexOf( strBuffer ) > -1 )
                {
                    strReturn += strBuffer;
                }
            }
            return strReturn;
        }


        function limitText(limitField, limitNum) {
            if (limitField.value.length > limitNum) {
                limitField.value = limitField.value.substring(0, limitNum);
            }
        }

    
        function convertText(r,t){
            var i = r.value;
            document.getElementById(t).value=i.toUpperCase();
        }
    
        function validateTelLength(value){
            var plength = value.length;
            if(plength < 7){
                alert('No. Telefon yang dimasukkan salah.');
                $('#noFaks').val("");
                $('#noFaks').focus();
            }
        }
    



 
</script>
<s:form beanclass="etanah.view.penguatkuasaan.NotisKosongkanTanahActionBean">
    <s:messages/>
    <input type="hidden" id="idOks" name="idOks" value="${actionBean.oks.idOrangKenaSyak}">
    <input type="hidden" id="idKertas" name="idKertas" value="${actionBean.kertas.idKertas}">
    <input type="hidden" id="addNotisFlag" name="addNotisFlag" value="${actionBean.addNotisFlag}">
    <input type="hidden" id="bil" name="bil" value="${actionBean.bilNotis}">

    <c:if test="${edit}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">

                    <div id="rujukan">
                        <p>
                            <label>No. Rujukan : </label>
                            ${actionBean.permohonan.idPermohonan}&nbsp;<br>
                            <label>Tarikh : </label>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.currentDate}"/>&nbsp;

                        </p>
                    </div>
                    <br><br><br>
                    <div id="alamat">
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.oks.nama}
                        </p>
                        <c:if test="${actionBean.oks.alamat.alamat1 ne null}"> 
                            <p>
                                <label>&nbsp;</label>
                                ${actionBean.oks.alamat.alamat1} <br>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat2 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat2}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat3 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat3}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat4 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat4}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.poskod ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.poskod}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.negeri.kod ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.negeri.nama}
                            </p>
                        </c:if>

                    </div>


                    <br>
                    <div id="kandungan">
                        <p>
                            Tuan,
                            <br><br>

                            <b>NOTIS UNTUK MENGOSONGKAN TANAH KERAJAAN<br>
                                <s:textarea name="tajuk" id="tajuk" rows="3" cols="100" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);"></s:textarea>

                                </p>
                                <br>
                                <p>
                                    Adalah dimaklumkan bahawa siasatan Pentadbiran ini mendapati tuan<br>
                                    <s:textarea name="makluman" id="makluman" rows="5" cols="100" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);"/>
                                </p>
                                <br>

                                <table>
                                    <tr>
                                        <td valign="top"><em>*</em><b>2.</b></td>
                                        <td>
                                            <s:textarea name="kandunganAwalan" id ="kandunganAwalan" rows="5" cols="100" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text"/>&nbsp;
                                        </td>
                                    </tr>

                                </table>


                                <tr><td>&nbsp;</td></tr>
                                <tr><td>
                                        <table id ="dataTable1">
                                            <c:set var="i" value="1" />
                                            <c:set var="k" value="3" />
                                            <c:set var="index" value="2" />
                                            <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                                <tr style="font-weight:bold">
                                                    <td valign="top"><em>*</em><c:out value="${k}." />

                                                        <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                                    <td> <s:textarea name="kandungan${i}" id="kandungan${i}"  rows="5" cols="100" value="${line.kandungan}" class="normal_text"/>
                                                    <td> <s:button class="btn" value="Hapus" name="delete" id="${i}" onclick="deleteRow('dataTable1',this.id)"/>
                                                    <td> <s:hidden name="idKand${i}" id="idKand${i}" value="noID"/>
                                                </tr>
                                                <c:set var="i" value="${i+1}" />
                                                <c:set var="k" value="${k+1}" />
                                            </c:forEach>

                                        </table>
                                    </td>
                                </tr>
                                <tr><td><s:hidden name="rowCount" value="${i-1}" id="rowCount"/>&nbsp;</td></tr>

                                <tr>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <s:button class="btn" value="Tambah" name="add" onclick="addRow1('dataTable1',2)"/>&nbsp;</td>
                                </tr>
                                <br><br>
                                <br>


                                <br>

                                <p>
                                    Sekian. Terima Kasih<br><br>
                                </p>
                                <p>
                                    Ditandatangan Oleh<br>
                                    <s:select name="diTandatanganOleh">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                                    </s:select>
                                </p>


                    </div>

                    <br><br>



                </div>
                <br/><br/>

            </fieldset>
        </div>

        <p align="center">
            <s:hidden name="idKertas"/>
            <s:button  name="reset" value="Isi Semula" class="btn" onclick="return test();"/>
            <s:button class="btn" onclick="if(validateForm())save(this.name,this.form);" name="simpan" value="Simpan"/>
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

        </p>
    </c:if>



    <c:if test="${view}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div class="content">

                    <div id="rujukan">
                        <p>
                            <label>No. Rujukan : </label>
                            ${actionBean.permohonan.idPermohonan}&nbsp;<br>
                            <label>Tarikh : </label>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${actionBean.currentDate}"/>&nbsp;

                        </p>
                    </div>
                    <br><br><br>
                    <div id="alamat">
                        <p>
                            <label>&nbsp;</label>
                            ${actionBean.oks.nama}
                        </p>
                        <c:if test="${actionBean.oks.alamat.alamat1 ne null}"> 
                            <p>
                                <label>&nbsp;</label>
                                ${actionBean.oks.alamat.alamat1} <br>
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat2 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat2}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat3 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat3}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.alamat4 ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.alamat4}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.poskod ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.poskod}
                            </p>
                        </c:if>
                        <c:if test="${actionBean.oks.alamat.negeri.kod ne null}"> 
                            <p>
                                <label> &nbsp;</label>
                                ${actionBean.oks.alamat.negeri.nama}
                            </p>
                        </c:if>

                    </div>


                    <br>
                    <div id="kandungan">
                        <p>
                            Tuan,
                            <br><br>

                            <b>NOTIS UNTUK MENGOSONGKAN TANAH KERAJAAN<br>
                                <s:textarea name="tajuk" id="tajuk" rows="3" cols="100" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);" readonly="true"></s:textarea>

                                </p>
                                <br>
                                <p>
                                    Adalah dimaklumkan bahawa siasatan Pentadbiran ini mendapati tuan<br>
                                    <s:textarea name="makluman" id="makluman" rows="5" cols="100" onkeydown="limitText(this,2500);" onkeyup="limitText(this,2500);" readonly="true"/>
                                </p>
                                <br>

                                <table>
                                    <tr>
                                        <td valign="top"><em>*</em><b>2.</b></td>
                                        <td>
                                            <s:textarea name="kandunganAwalan" id ="kandunganAwalan" rows="5" cols="100" onkeydown="limitText(this,1000);" onkeyup="limitText(this,1000);" class="normal_text" readonly="true"/>&nbsp;
                                        </td>
                                    </tr>

                                </table>


                                <tr><td>&nbsp;</td></tr>
                                <tr><td>
                                        <table id ="dataTable1">
                                            <c:set var="i" value="1" />
                                            <c:set var="k" value="3" />
                                            <c:set var="index" value="2" />
                                            <c:forEach items="${actionBean.senaraiKertas}" var="line">
                                                <tr style="font-weight:bold">
                                                    <td valign="top"><em>*</em><c:out value="${k}." />

                                                        <input type="hidden" id="idKand${i}" value="${line.idKandungan}">
                                                    <td> <s:textarea name="kandungan${i}" id="kandungan${i}"  rows="5" cols="100" value="${line.kandungan}" class="normal_text" readonly="true"/>
                                                    <td> 
                                                    <td> <s:hidden name="idKand${i}" id="idKand${i}" value="noID"/>
                                                </tr>
                                                <c:set var="i" value="${i+1}" />
                                                <c:set var="k" value="${k+1}" />
                                            </c:forEach>

                                        </table>
                                    </td>
                                </tr>
                                <tr><td><s:hidden name="rowCount" value="${i-1}" id="rowCount"/>&nbsp;</td></tr>

                                <tr>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                </tr>
                                <br><br>
                                <br>


                                <br>

                                <p>
                                    Sekian. Terima Kasih<br><br>
                                </p>
                                <p>
                                    Ditandatangan Oleh<br>
                                    <s:select name="diTandatanganOleh" disabled="true">
                                        <s:option value="">Sila Pilih</s:option>
                                        <s:options-collection collection="${actionBean.senaraiPengguna}" label="nama" value="idPengguna" />
                                    </s:select>
                                </p>
                    </div>

                    <br><br>

                </div>
                <br/><br/>

            </fieldset>
        </div>

        <p align="center">
            <s:button name="tutup" id="tutup" value="Tutup" class="btn" onclick="self.close()"/>

        </p>


    </c:if>







</s:form>
